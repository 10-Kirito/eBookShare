<template>
<div>
  <!--  首页主要组成部分-->
  <div style="margin-bottom: 10px" class="header-with-background">
    <el-row style="margin-bottom:30px">
      <div style="margin-top: 50px">
        <input class="input is-rounded" type="text" placeholder="输入关键字搜索图书" style="width: 40%; font-size: 25px" v-model="searchQuery">

        <button class="button is-success is-rounded" @click="searchBooks" style="font-size: 26px; margin-left: 10px">查询</button>
      </div>

    </el-row>
    <el-row>
      <!-- 左侧显示内容-->
      <el-col :span="6" style="color: #42b983">
        <div class="grid-content bg-purple" >
          <el-card class="box-card" style="margin-right: 10px; height: 1410px">
            <h1>图书总榜单</h1>

            <el-card v-for="(book, index) in booksDownloadList" :key="index"  :class="index % 2 === 0 ? 'even' : 'odd'">
                <el-row @mouseenter.native="showInfo(index)" @mouseleave.native="hideInfo(index)" @click.native="pushDetail(booksDownloadList[index])" style="cursor: pointer;">
                <el-col :span="8">
                  <img :src="book.img" class="book-cover"/>
                </el-col>
                <el-col :span="16" class="book-info">
                  <div class="book-title">{{ book.title }}</div>
                  <div class="book-download">{{ book.downloadCount }}次下载</div>
                  <div class="book-details" v-show="showLeftDetails===index">
                    <div class="book-author"><span @click="redirectToAuthorBooks(book.author)" title="找到该作者的所有书籍" class="book-author">{{book.author}}</span></div>
                    <div class="book-category" title="类别">{{ book.category }}</div>
                  </div>
                </el-col>
              </el-row>
            </el-card>

          </el-card>
        </div>
      </el-col>

      <!--用来展示推荐书籍的地方，以卡片的方式进行展示！，每一个卡片上都有对应书籍的信息！-->
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <h1>今日推荐阅读图书：</h1>

          <el-card class="box-card" v-for="(book,index) in bookDetails" :key="index" style="margin-bottom: 15px">
            <el-row :gutter="20">
              <el-col :span="6"  >
                <el-image :src="book.coverimage" @click="pushDetail(bookDetails[index])" fit="contain" lazy style="cursor: pointer;height: 150px;"/>
              </el-col>
              <el-col :span="15" >
                <el-row class="attriclass" >
                  <el-col :span="23">
                    <h3 @click="pushDetail(bookDetails[index])" style="cursor: pointer;text-decoration: underline">{{ book.bookname }}</h3>
                  </el-col>
                  <el-col :span="1">
                    <el-tooltip class="item" effect="dark" :content="book.isCollected ? '取消收藏' : '收藏' ">
                      <i :class="book.collectBtnClass" @click="collectBtn(index,book)" style="cursor: pointer;font-size: 25px;margin-left: 18px;margin-top: 20px"></i>
                    </el-tooltip>
                  </el-col>
                </el-row>
                <el-row style="margin-left: -40px;padding-top: -10px"><div class="grid-content book-publisher" title="出版社">{{book.publisher}}</div></el-row>
                <el-row class="attriclass"><span @click="redirectToAuthorBooks(book.author)" title="找到该作者的所有书籍" class="book-author">{{book.author}}</span></el-row>
                <el-row class="attriclass" style="margin-top: 40px">
                  <el-col :span="12" ><div style="color: #8C8C8C; ">标准书号: {{book.isbn}}</div></el-col>
                  <el-col :span="8" ><div style="color: #8C8C8C;">类别：{{book.category}}</div></el-col>
                  <el-col :span="4" ><div style="color: #8C8C8C;">文件：{{book.type}}</div></el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-col>

      <!--右侧显示内容-->
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <div class="grid-content bg-purple" >
            <el-card class="box-card" style="margin-left: 10px; height: 1410px">
              <h1>今日最受欢迎电子图书</h1>
              <el-card v-for="(book, index) in todayPopularBooks" :key="index"  :class="index % 2 === 0 ? 'even' : 'odd'">
                <el-row v-if="book" @mouseenter.native="showInfo2(index)" @mouseleave.native="hideInfo2(index)" @click.native="pushDetail(todayPopularBooks[index])" style="cursor: pointer;">
                  <el-col :span="8">
                    <img :src="book.img" class="book-cover"/>
                  </el-col>
                  <el-col :span="16" class="book-info">
                    <div class="book-title">{{ book.title }}</div>
                    <div class="book-download">{{ book.downloadCount }}次下载</div>
                    <div class="book-details" v-show="showRightDetails===index">
                      <div class="book-author"><span @click="redirectToAuthorBooks(book.author)" title="找到该作者的所有书籍" class="book-author">{{book.author}}</span></div>
                      <div class="book-category" title="类别">{{ book.category }}</div>
                    </div>
                  </el-col>
                </el-row>
              </el-card>
            </el-card>
          </div>
        </div>
      </el-col>

    </el-row>
  </div>
</div>
</template>

<script>
import router from "@/router";
import Vue from "vue";

export default {
  inject:['reload'],
  name: "Home",
  data() {
    return {
      // 用户个人信息
      user:{},

      // 今日推荐图书信息:
      recommendBooks:[],
      recommendNumber: 10,

      flag: true,


      pageTitle: '上海大学电子图书分享平台',
      //searchHTML: 'static/search/index.html',
      //书籍信息保存在这个数组中，//今日推荐阅读图书信息
      bookDetails: [],
      //图书总榜单信息，按图书下载量排行
      booksDownloadList:[
        {
          img: "https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",
          title: "Book Title",
          author: "Author Name",
          publisher: "Publisher Name",
          publishedDate: "2022-01-01",
          description: "《麦田里的守望者》的主人公,16岁的中学生霍尔顿·考尔菲德是当代美国文学中最早出现的反英雄形象之一。霍尔顿出身在纽约一个富裕的中产阶级的家庭。学校里的老师和自己的家长强迫他好好读书,为的是“出人头地,而他看不惯周围的一切,根本没心思用功读书,因而老是挨罚。他的内心又十分苦闷、彷徨,这种精神上无法调和的极度矛盾最终令他彻底崩溃,躺倒在精神病院里。",
          ISBN: "978-3-16-148410-0",
          Format: "Hardcover",
          Pages: "400",
          Language: "English",
          category:"category",
          file:'PDF',
          isCollected:false,
          downloadCount:"48"
        },
        {
          img: "https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",
          title: "Book Title 2",
          author: "Author Name 2",
          publisher: "Publisher Name 2",
          publishedDate: "2022-01-01",
          description:
              "Description of Book 2",
          ISBN: "ISBN 2",
          Format: "Hardcover",
          Pages: "300",
          Language: "English",
          category: "category 2",
          file: "PDF",
          isCollected:false,
          downloadCount:"44"
        }
      ],
      //今日最受欢迎图书信息，按照今日下载量和收藏量排行
      todayPopularBooks:[
        {
          img: "https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",
          title: "Book Title",
          author: "Author Name",
          publisher: "Publisher Name",
          publishedDate: "2022-01-01",
          description: "《麦田里的守望者》的主人公,16岁的中学生霍尔顿·考尔菲德是当代美国文学中最早出现的反英雄形象之一。霍尔顿出身在纽约一个富裕的中产阶级的家庭。学校里的老师和自己的家长强迫他好好读书,为的是“出人头地,而他看不惯周围的一切,根本没心思用功读书,因而老是挨罚。他的内心又十分苦闷、彷徨,这种精神上无法调和的极度矛盾最终令他彻底崩溃,躺倒在精神病院里。",
          ISBN: "978-3-16-148410-0",
          Format: "Hardcover",
          Pages: "400",
          Language: "English",
          category:"category",
          file:'PDF',
          isCollected:false,
          downloadCount:"48"
        },
        {
          img: "https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",
          title: "Book Title 2",
          author: "Author Name 2",
          publisher: "Publisher Name 2",
          publishedDate: "2022-01-01",
          description:
              "Description of Book 2",
          ISBN: "ISBN 2",
          Format: "Hardcover",
          Pages: "300",
          Language: "English",
          category: "category 2",
          file: "PDF",
          isCollected:false,
          downloadCount:"44"
        }
      ],
      searchQuery:'',
      // total:0,
      // pageNum:1,
      // pageSize:10,
      showLeftDetails: -1 ,// 当前显示详情的左侧图书索引
      showRightDetails: -1, // 当前显示详情的右侧图书索引
    };
  },
  created() {
    this.getUser();
    this.getRecommendBooks();
  },
  beforeMount() {

  },
  methods: {
    //获取用户信息
    getUser(){
      const data = JSON.parse(localStorage.getItem('loguserinfo'))
      if (data) {
        Vue.set(this, 'user', data);
      }
    },
    searchBooks() {
      //跳转到搜索结果页进行搜索
      router.push({
        path:'/searchResult',
        //路由传递参数，将搜索关键字传递过去搜索
        query:{params:JSON.stringify(this.searchQuery)}
      })
    },
    getRecommendBooks(){//分页查询今日推荐的图书
      this.request.get("http://localhost:9091/books/random", {           //更改后台接口
        params: {
          number: this.recommendNumber
        }
      }).then(res => {
        console.log(res.data);
        this.bookDetails = res.data                   //根据后台数据更改
        this.bookDetails.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性 和 collectBtnClass 属性
          book.isCollected = false; // 默认值为 false
          book.collectBtnClass = "el-icon-star-off"     //默认值关闭
        })
      })
    },
    pushDetail(index){           //跳转路由函数
      // console.log(JSON.stringify(this.bookDetails[0]))
      router.push({
        path:'/bookdetails',
        //路由传递参数，将书的信息传递到书籍详情页
        query:{params:JSON.stringify(index)}
      })
    },
    redirectToAuthorBooks(author){          //将作者作为参数跳转到 找到作者的所有书籍 页面
      this.$router.push({
        path:'/authorBooks',
        query:{params:JSON.stringify(author)}
      })
    },
    collectBtn(index,book){//收藏
      this.bookDetails[index].isCollected=!this.bookDetails[index].isCollected
      if(this.bookDetails[index].isCollected){
        this.$message({
          message: '收藏成功',
          type: 'success'
        });
        this.bookDetails[index].collectBtnClass="el-icon-star-on";

        this.request.get("http://localhost:9091/relationship/favourbook", {
          params:{
            bookid: book.bookid,
            userid: this.user.id
          }
        }).then(response => {
          console.log(response);
        })
        console.log("收藏");
      }else{  //取消收藏
        this.$message({
          message: '取消成功',
          type: 'success'
        });
        this.bookDetails[index].collectBtnClass="el-icon-star-off";

        this.request.get("http://localhost:9091/relationship/favourbook", {
          params:{
            bookid: book.bookid,
            userid: this.user.id
          }
        }).then(response => {
          console.log(response);
        })
        console.log("取消收藏");
      }
      this.$forceUpdate()
    },
    showInfo(index) {
      // console.log("this has been enter")
      this.showLeftDetails = index
    },
    hideInfo(index) {
      // console.log("this has been out")
      this.showLeftDetails = -1
    },
    showInfo2(index) {
      // console.log("this has been enter")
      this.showRightDetails = index
    },
    hideInfo2(index) {
      // console.log("this has been out")
      this.showRightDetails = -1
    }
  }
}
</script>

<style scoped>




.book-cover {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.book-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 10px;
}

.book-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.book-download {
  font-size: 14px;
  color: #999;
  margin-bottom: 5px;
}

.book-details {
  font-size: 14px;
  color: #999;
  margin-top: 5px;
  display: block;
}

.book-category {
  margin-bottom: 5px;
}

.even {
  background-color: #f7f7f7;
}

.odd {
  background-color: #fff;
}

.book-publisher{
  color: #8C8C8C;
  margin-bottom:10px;
  cursor: pointer;
}
.book-author {
  font-size: 17px;
  color: #49afd0;
  font-style: italic;
  font-family: Helvetica;
  cursor: pointer;
  text-decoration: none;
  margin-bottom: 5px;
}
.attriclass{
  padding: 0;
  margin: -10px -10px -10px -40px;
}

.grid-content {
  text-align: left;
  margin-left: 0px;
  border-radius: 4px;
  min-height: 36px;
}

h1 {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 16px;
}
.header-with-background {
  background-image: url('~@/assets/images/whiteback.jpg');
  /*background-size: cover;*/
}
</style>

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
          <el-card class="box-card" style="margin-right: 10px; height: 100%">
            <h1>图书总榜单</h1>

            <el-card v-for="(book, index) in booksDownloadList" :key="index"  :class="index % 2 === 0 ? 'even' : 'odd'">
                <el-row @mouseenter.native="showInfo(index)" @mouseleave.native="hideInfo(index)" @click.native="pushDetail(booksDownloadList[index])" style="cursor: pointer;">
                <el-col :span="8">
                  <img :src="book.coverimage" class="book-cover" />
                </el-col>
                <el-col :span="16" class="book-info">
                  <div class="book-title">{{ book.bookname }}</div>
                  <div class="book-download">{{ book.downloads }}次下载</div>
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
                <el-image :src="book.coverimage" @click="pushDetail(bookDetails[index])" fit="contain" style="cursor: pointer;height: 150px;" lazy/>
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
            <el-card class="box-card" style="margin-left: 10px; height: 100%">
              <h1>图书下载榜单</h1>
              <el-card v-for="(book, index) in todayPopularBooks" :key="index"  :class="index % 2 === 0 ? 'even' : 'odd'">
                <el-row v-if="book" @mouseenter.native="showInfo2(index)" @mouseleave.native="hideInfo2(index)" @click.native="pushDetail(todayPopularBooks[index])" style="cursor: pointer;">
                  <el-col :span="8">
                    <img :src="book.coverimage" class="book-cover"/>
                  </el-col>
                  <el-col :span="16" class="book-info">
                    <div class="book-title">{{ book.bookname }}</div>
                    <div class="book-download">{{ book.downloads }}次下载</div>
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

      flag: true,


      pageTitle: '上海大学电子图书分享平台',

      //书籍信息保存在这个数组中，//今日推荐阅读图书信息
      bookDetails: [],
      recommendNumber: 7,

      //图书总榜单信息，按图书下载量+收藏量+点赞量排行
      booksDownloadList:[],

      //图书下载榜单信息，按照下载量排行
      todayPopularBooks:[],
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

    this.getDowndoadTop();
    this.getTotalList();
  },
  methods: {
    //获取用户信息
    getUser(){
      const data = localStorage.getItem('loguserinfo')?JSON.parse(localStorage.getItem('loguserinfo')): {id:0}
      if (data) {
        Vue.set(this, 'user', data);
      }
    },

    // 获取图书总榜单信息
    getTotalList(){
      this.request.get("/books/overallbooklist",{
        params: {
          userid:this.user.id
        }
      }).then(res => {
        console.log(res);
        this.booksDownloadList = res.data;
        this.booksDownloadList.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性 和 collectBtnClass 属性
          book.collectBtnClass = "el-icon-star-off"
          if(book.isfavour==1){
            book.isCollected = true;
            book.collectBtnClass = "el-icon-star-on"
          }
          else
            book.isCollected = false;
          if(book.islike==1)
            book.islike=true;
          else
            book.islike=false;
          //默认值关闭
        })
      })
    },
    // 获取图书下载的总榜单
    getDowndoadTop(){
      this.request.get("/books/downloadBooks",{
        params: {
          userid:this.user.id
        }
      }).then(res => {
        console.log(res);
        this.todayPopularBooks = res.data;
        this.todayPopularBooks.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性 和 collectBtnClass 属性
          book.collectBtnClass = "el-icon-star-off"
          if(book.isfavour==1){
            book.isCollected = true;
            book.collectBtnClass = "el-icon-star-on"
          }
          else
            book.isCollected = false;
          if(book.islike==1)
            book.islike=true;
          else
            book.islike=false;
          //默认值关闭
        })
      })
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
      this.request.get("/books/random", {           //更改后台接口
        params: {
          number: this.recommendNumber,
          userid:this.user.id
        }
      }).then(res => {
        console.log(res.data);
        this.bookDetails = res.data                   //根据后台数据更改
        this.bookDetails.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性 和 collectBtnClass 属性
          book.collectBtnClass = "el-icon-star-off"
          if(book.isfavour==1){
            book.isCollected = true;
            book.collectBtnClass = "el-icon-star-on"
          }
          else
            book.isCollected = false;
          if(book.islike==1)
            book.islike=true;
          else
            book.islike=false;
          //默认值关闭
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
        path:'/searchResult',
        query:{params:JSON.stringify(author)}
      })
    },
    collectBtn(index,book){//收藏
      if(this.user.id==0){
        this.$message.error("请先登录");
        return;
      }


      this.bookDetails[index].isCollected=!this.bookDetails[index].isCollected
      if(this.bookDetails[index].isCollected){
        this.$message({
          message: '收藏成功',
          type: 'success'
        });
        this.bookDetails[index].collectBtnClass="el-icon-star-on";

        this.request.get("http://61.171.68.199:9091/relationship/favourbook", {
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

        this.request.get("http://61.171.68.199:9091/relationship/favourbook", {
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

<template>
<div>
  <!--  首页主要组成部分-->
  <div style="margin-bottom: 10px; margin-top: 20px">
    <el-row style="margin-bottom:30px">
      <div>
        <input type="text" v-model="searchQuery" placeholder="输入关键字搜索图书" class="search ">
        <el-button @click="searchBooks" type="success" class="searchButton" >查询</el-button>
      </div>

    </el-row>
    <el-row>
      <!-- 左侧显示内容-->
      <el-col :span="6" style="color: #42b983">
        <div class="grid-content bg-purple" >
          <el-card class="box-card" style="margin-right: 10px; height: 1410px">
            <h1>图书总榜单</h1>

            <el-card v-for="(book, index) in booksDownloadList" :key="index"  :class="index % 2 === 0 ? 'even' : 'odd'" @mouseenter="showInfo(index)" @mouseleave="hideInfo(index)">
              <el-row >
                <el-col :span="8">
                  <img :src="book.img" class="book-cover" />
                </el-col>
                <el-col :span="16" class="book-info">
                  <div class="book-title">{{ book.title }}</div>
                  <div class="book-download">{{ book.downloadCount }}次下载</div>
                  <div class="book-details" v-show="showDetails === index">
                    <div class="book-author">{{ book.author }}</div>
                    <div class="book-category">{{ book.category }}</div>
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

          <el-card class="box-card" v-for="(book,index) in bookDetails" :key="index">
            <el-row :gutter="20">
              <el-col :span="6"  >
                <el-image :src="book.img" @click="pushDetail(index)" fit="contain" lazy style="cursor: pointer;height: 150px;"/>
              </el-col>
              <el-col :span="15" >
                <el-row class="attriclass" >
                  <el-col :span="23">
                    <h3 @click="pushDetail(index)" style="cursor: pointer;text-decoration: underline">{{ book.title }}</h3>
                  </el-col>
                  <el-col :span="1">
                    <el-tooltip class="item" effect="dark" :content="book.isCollected ? '取消收藏' : '收藏' ">
                      <i :class="[book.isCollected ? 'el-icon-star-on' : 'el-icon-star-off']" @click="collectBtn(index)" style="cursor: pointer;font-size: 25px;margin-left: 18px;margin-top: 20px"></i>
                    </el-tooltip>
                  </el-col>
                </el-row>
                <el-row class="attriclass"><div class="grid-content book-publisher" title="出版社">{{book.publisher}}</div></el-row>
                <el-row class="attriclass"><a href="/author/J. D. Salinger" title="找到该作者的所有书籍" class="book-author">{{book.author}}</a></el-row>
                <el-row :gutter="24" style="margin-top: 40px;margin-right: -70px">
                  <el-col :span="2" ><div style="color: #8C8C8C;">年:</div></el-col>
                  <el-col :span="6" ><div style="margin-top: 2px">{{book.publishedDate}}</div></el-col>
                  <el-col :span="4" ><div style="color: #8C8C8C;">语言：</div></el-col>
                  <el-col :span="4" ><div style="margin-top: 2px">{{book.Language}}</div></el-col>
                  <el-col :span="4" ><div style="color: #8C8C8C;">文件：</div></el-col>
                  <el-col :span="4" ><div style="margin-top: 2px">{{book.file}}</div></el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-card>


          <!--分页选项-->
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[2,5,10,20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </el-col>

      <!--右侧显示内容-->
      <el-col :span="6">
        <div class="grid-content bg-purple-light">
          <div class="grid-content bg-purple" >
            <el-card class="box-card" style="margin-left: 10px; height: 1410px">
              <h1>今日最受欢迎电子图书！！</h1>
              <p>
                这里显示今天下载量和收藏量最高的图书!!!
              </p>
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

export default {
  name: "Home",
  data() {
    return {
      pageTitle: '上海大学电子图书分享平台',
      //searchHTML: 'static/search/index.html',
      //书籍信息保存在这个数组中，//今日推荐阅读图书信息
      bookDetails: [
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
      }
      ],
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
      searchQuery:'',
      total:0,
      pageNum:1,
      pageSize:10,
      showDetails: -1 // 当前显示详情的图书索引
    };
  },
  created() {
  },
  methods: {
    searchBooks() {
      // 这里可以向服务器发起搜索请求，获取匹配的图书列表
      router.push({
        path:'/searchResult',
        //路由传递参数，将书的信息传递到书籍详情页
        query:{params:JSON.stringify(this.searchQuery)}
      })
    },
    load(){//分页查询今日推荐的图书
      this.request.get("/",{           //更改后台接口
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
        }
      }).then(res=>{
        this.bookDetails=res.records           //根据后台数据更改
        this.total=res.total
        this.bookDetails.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性
          book.isCollected = false; // 默认值为 false
        })
      })
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    pushDetail(index){           //跳转路由函数
      // console.log(JSON.stringify(this.bookDetails[0]))
      router.push({
        path:'/bookdetails',
        //路由传递参数，将书的信息传递到书籍详情页
        query:{params:JSON.stringify(this.bookDetails[index])}
      })
    },
    collectBtn(index){//收藏
      this.bookDetails[index].isCollected = !this.bookDetails[index].isCollected;
    },
    showInfo(index) {
      this.showDetails = index
    },
    hideInfo(index) {
      this.showDetails = -1
    }
  },
}
</script>

<style scoped>

.search{
  height: 55px;
  width: 600px;
  font-size: 20px;
}
.searchButton{
  height: 55px;
  width: 100px;
  font-size: 20px;
  margin-left: 10px;
}


/*以下李洪辰代码不能动!*/

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
  display: none;
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
  font-size: 10pt;
  margin-bottom: 5px;
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



</style>

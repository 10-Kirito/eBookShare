<template>
  <div>
    <el-container>
      <!--   首页头部   -->
      <el-header>
        <el-col :span="12" :offset="1" align="left"><div class="grid-content bg-purple">{{ pageTitle }}</div></el-col>
        <el-row type="flex" justify="end">
          <el-col :span="8"><router-link to="/">首页</router-link></el-col>
          <el-col :span="8"><router-link to="/">个人书架</router-link></el-col>
          <el-col :span="8"><router-link to="/">登录</router-link></el-col>
        </el-row>
      </el-header>

      <!--  首页主要组成部分-->
      <el-main class="el-main">
        <div style="margin-bottom: 10px; margin-top: 20px">
          <el-row style="margin-bottom:30px">
            <input type="text" v-model="searchQuery" placeholder="输入关键字搜索图书">
            <button @click="searchBooks" style="height: 64px;width: 100px; font-size: 20px">查询</button>
          </el-row>
          <!-- 走马灯设置-->
<!--          <div style="margin-top: 20px">-->
<!--            <el-carousel :interval="4000" type="card" height="400px">-->
<!--              <el-carousel-item v-for="item in 6" :key="item">-->
<!--                <h3 class="medium">{{ item }}</h3>-->
<!--              </el-carousel-item>-->
<!--            </el-carousel>-->
<!--          </div>-->

          <el-row>
            <!-- 左侧显示内容-->
            <el-col :span="6" style="color: #42b983">
              <div class="grid-content bg-purple" >
                <el-card class="box-card" style="margin-right: 10px; height: 1410px">
                  <h1>图书总榜单</h1>

                  <p>
                    这里显示所有的图书的下载量!!!
                  </p>
                </el-card>
              </div>
            </el-col>

            <!--用来展示推荐书籍的地方，以卡片的方式进行展示！，每一个卡片上都有对应书籍的信息！-->
            <el-col :span="12">
              <div class="grid-content bg-purple-light">
                <h1>今日推荐阅读图书：</h1>

                <el-card class="box-card">
                  <el-row :gutter="20">
                    <el-col :span="6" class="book-cover" >
                        <el-image :src="bookDetails[0].img" fit="contain" lazy style="height: 150px;"/>
                    </el-col>
                    <el-col :span="10" >
                      <el-row class="attriclass" ><h3 @click="pushDetail" style="cursor: pointer">{{ bookDetails[0].title }}</h3></el-row>
                      <el-row class="attriclass"><div class="grid-content ">{{bookDetails[0].publisher}}</div></el-row>
                    </el-col>
                  </el-row>
                </el-card>

                <el-card class="box-card" style="margin-top: 10px">
                  <div v-for="o in 4" :key="o" style="font-size: 14px; padding: 18px 0; width: 480px;">
                    {{'列表内容 ' + o }}
                  </div>
                </el-card>

                <el-card class="box-card" style="margin-top: 10px">
                  <div v-for="o in 4" :key="o" style="font-size: 14px; padding: 18px 0; width: 480px;">
                    {{'列表内容 ' + o }}
                  </div>
                </el-card>

                <el-card class="box-card" style="margin-top: 10px">
                  <div v-for="o in 4" :key="o" style="font-size: 14px; padding: 18px 0; width: 480px;">
                    {{'列表内容 ' + o }}
                  </div>
                </el-card>

                <el-card class="box-card" style="margin-top: 10px">
                  <div v-for="o in 4" :key="o" style="font-size: 14px; padding: 18px 0; width: 480px;">
                    {{'列表内容 ' + o }}
                  </div>
                </el-card>

                <!--分页选项-->
                <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="1000"
                    style="margin-top: 30px">
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
      </el-main>

      <!--  首页下半部分-->
      <el-footer height="100px">
        <div>
          <h1>@上海大学电子图书分享平台</h1>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>



<script>
import bookDetails from "@/views/Books/BookDetails.vue";
import router from "@/router/index.js"


export default {
  name: "Layout",
  data() {
    return {
      pageTitle: '上海大学电子图书分享平台',
      bookDetails: [{
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
        category:"category"
      }],
      searchQuery:''
    };
  },
  methods: {
    searchBooks() {
      // 这里可以向服务器发起搜索请求，获取匹配的图书列表
    },
    pushDetail(){
      // console.log(JSON.stringify(this.bookDetails[0]))
      router.push({
        path:'/bookdetails',
        query:{params:JSON.stringify(this.bookDetails[0])}
      })
    }
  },
}
</script>

<style scoped>

.attriclass{
  padding: 0;
  margin: 0;
}

.align-left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 10px;
}

.grid-content {
  text-align: left;
  margin-left: 0px;
  border-radius: 4px;
  min-height: 36px;
}

.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}


.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 700px;*/
}

body > .el-container {
  margin-bottom: 40px;
}
h1 {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 16px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 16px;
}

h2 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 8px;
}

p {
  font-size: 16px;
  margin: 0;
}

input[type="text"] {
  font-size: 16px;
  padding: 8px;
  margin-right: 8px;
  width: 700px;
  height: 50px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  font-size: 16px;
  padding: 8px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  height: 50px;
  width: 100px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>


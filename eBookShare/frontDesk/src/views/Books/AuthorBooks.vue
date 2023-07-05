<template>

  <div>

    <el-main>
    <h1>{{author}}的书籍:</h1>

      <div style="margin: 0 100px">
        <div>
          <div>
            <div class="grid-content bg-purple-light">

              <el-card class="box-card" v-for="(book,index) in bookDetails" :key="index">
                <el-row :gutter="20">
                  <!--              <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">-->
                  <el-col :span="6" class="book-cover" >
                    <el-image :src="book.img" fit="contain" @click="pushDetail(index)" lazy style="cursor: pointer;height: 160px;margin-left: 80px"/>
                  </el-col>
                  <el-col :span="15" >
                    <el-row class="attriclass" >
                      <el-col :span="23">
                        <h3 @click="pushDetail(index)" style="cursor: pointer;text-decoration: underline">{{ book.title }}</h3>
                      </el-col>
                      <el-col :span="1">
                        <el-tooltip class="item" effect="dark" :content="book.isCollected ? '取消收藏' : '收藏' ">
                          <i :class="book.collectBtnClass" @click="collectBtn(index)" style="cursor: pointer;font-size: 25px;margin-left: 18px;margin-top: 20px"></i>
                        </el-tooltip>
                      </el-col>
                    </el-row>
                    <el-row class="attriclass"><div class="grid-content book-publisher" title="出版社">{{book.publisher}}</div></el-row>
<!--                    <el-row class="attriclass"><a href="/author/J. D. Salinger" title="找到该作者的所有书籍" class="book-author">{{book.author}}</a></el-row>-->
                    <el-row :gutter="24" style="margin-top: 60px;margin-right: -70px">
                      <el-col :span="2" ><div style="color: #8C8C8C;">年:</div></el-col>
                      <el-col :span="6" ><div style="margin-top: 2px">{{book.publishedDate}}</div></el-col>
                      <el-col :span="4" ><div style="color: #8C8C8C;">语言：</div></el-col>
                      <el-col :span="4" ><div style="margin-top: 2px">{{book.Language}}</div></el-col>
                      <el-col :span="4" ><div style="color: #8C8C8C;">文件：</div></el-col>
                      <el-col :span="4" ><div style="margin-top: 2px">{{book.file}}</div></el-col>
                    </el-row>
                  </el-col>
                </el-row>
                <!--              </div>-->
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
          </div>
        </div>
      </div>
    </el-main>
  </div>


</template>

<script>
import router from "@/router";

export default {
  name: "AuthorBooks",
  data(){
    return{
      author:JSON.parse(this.$route.query.params),//搜索关键字
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
        category: "category",
        file: 'PDF',
        isCollected: false,
        collectBtnClass:"el-icon-star-off"
      },
        {
          img: "https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",
          title: "Book Title 3",
          author: "Author Name ",
          publisher: "Publisher Name 3",
          publishedDate: "2022-01-01",
          description:
              "Description of Book 23",
          ISBN: "ISBN 3",
          Format: "Hardcover",
          Pages: "300",
          Language: "English",
          category: "category 3",
          file: "PDF",
          isCollected: false,
          collectBtnClass:"el-icon-star-off"
        }],
      total: 0,
      pageNum: 1,
      pageSize: 10,
    }
  },
  created() {
    //load()
  },
  methods: {
    load() {             //根据作者名字查询书籍
      this.request.get("/", {           //更改后台接口
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          auther: this.author
        }
      }).then(res => {
        this.bookDetails = res.records           //根据后台数据更改
        this.total = res.total
        this.bookDetails.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性
          book.isCollected = false; // 默认值为 false
          book.collectBtnClass = "el-icon-star-off"     //默认值关闭
        })
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    pushDetail(index) {           //跳转路由函数
      // console.log(JSON.stringify(this.bookDetails[0]))
      router.push({
        path: '/bookdetails',
        //路由传递参数，将书的信息传递到书籍详情页
        query: {params: JSON.stringify(this.bookDetails[index])}
      })
    },
    collectBtn(index) {//收藏
      this.bookDetails[index].isCollected = !this.bookDetails[index].isCollected
      if (this.bookDetails[index].isCollected) {
        this.$message.success('收藏成功');
        this.bookDetails[index].collectBtnClass = "el-icon-star-on"
      } else {  //取消收藏
        this.$message.success('取消成功');
        this.bookDetails[index].collectBtnClass = "el-icon-star-off"
      }
    }
  }
}
</script>

<style scoped>
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  text-align: left;
  margin-left: 0px;
  border-radius: 4px;
  min-height: 36px;
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
}

.attriclass{
  padding: 0;
  margin: -10px -10px -10px -40px;
}
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
}
</style>
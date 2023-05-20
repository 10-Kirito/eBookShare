<template>
  <div>
    <el-main class="el-main">
      <div style="margin-bottom: 10px; margin-top: 20px">
        <el-row style="margin-bottom:30px">
          <input class="input is-rounded" type="text" placeholder="输入关键字搜索图书" style="width: 40%; font-size: 25px" v-model="searchQuery">
          <button class="button is-success is-rounded" @click="searchBooks" style="font-size: 26px; margin-left: 10px">查询</button>
        </el-row>
      </div>

      <div style="margin: 0 100px">
        <div>
          <div>
            <div class="grid-content bg-purple-light">

              <el-card class="box-card" v-for="(book,index) in bookDetails" :key="index">
                <el-row :gutter="20">
                  <!--              <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">-->
                  <el-col :span="6" class="book-cover" >
                    <el-image :src="book.coverimage" fit="contain" @click="pushDetail(index)" lazy style="cursor: pointer;height: 160px;margin-left: 80px"/>
                  </el-col>
                  <el-col :span="15" >
                    <el-row class="attriclass" >
                      <el-col :span="23">
                        <h3 @click="pushDetail(index)" style="cursor: pointer;text-decoration: underline">{{ book.bookname }}</h3>
                      </el-col>
                      <el-col :span="1">
                        <el-tooltip class="item" effect="dark" :content="book.isCollected ? '取消收藏' : '收藏' ">
                          <i :class="book.collectBtnClass" @click="collectBtn(index)" style="cursor: pointer;font-size: 25px;margin-left: 18px;margin-top: 20px"></i>
                        </el-tooltip>
                      </el-col>
                    </el-row>
                    <el-row class="attriclass"><div class="grid-content book-publisher" title="出版社">{{book.publisher}}</div></el-row>
                    <el-row class="attriclass"><span @click="redirectToAuthorBooks(book.author)" title="找到该作者的所有书籍" class="book-author">{{book.author}}</span></el-row>
                    <el-row :gutter="24" style="margin-top: 40px;margin-right: -70px">
                      <el-col :span="2" ><div style="color: #8C8C8C;">年:</div></el-col>
                      <el-col :span="6" ><div style="margin-top: 2px">{{book.releasetime}}</div></el-col>
<!--                      <el-col :span="4" ><div style="color: #8C8C8C;">语言：</div></el-col>
                      <el-col :span="4" ><div style="margin-top: 2px">{{book.Language}}</div></el-col>-->
                      <el-col :span="4" ><div style="color: #8C8C8C;">文件：</div></el-col>
                      <el-col :span="4" ><div style="margin-top: 2px">{{book.type}}</div></el-col>
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
  name: "SearchResult",
  data() {
    return {
      pageTitle: '上海大学电子图书分享平台',
      //书籍信息保存在这个数组中
      bookDetails: [],
      searchQuery: '',
      total: 0,
      pageNum: 1,
      pageSize: 10,
      user:localStorage.getItem("loguserinfo")?JSON.parse(localStorage.getItem("loguserinfo")):"",
    };
  },
  created() {
    const params = this.$route.query.params;   //获取搜索关键字
    if (params) {
      try {
        this.searchQuery = JSON.parse(params);
        console.log('数据是：', this.searchQuery);
      } catch (e) {
        console.error('解析参数出错：', e);
      }
    } else {
      console.warn('未找到参数');
    }

    this.searchBooks();
    //this.load()        //加载初始数据
  },
  methods: {
    searchBooks() {
        this.request.get("/FrontBooks/search", {           //更改后台接口
            params: {
                bookinfo: this.searchQuery,
                userid: this.user.id,
                pageNum:this.pageNum,
                pageSize:this.pageSize
            }
        }).then(res => {
            console.log(res.data);
            this.bookDetails = res.data                 //根据后台数据更改
            this.bookDetails.forEach(book => {//遍历数组，为每个对象添加 isCollected 属性 和 collectBtnClass 属性
                book.collectBtnClass = "el-icon-star-off"
                if(book.favorites==-1){
                    book.isCollected = true;
                    book.collectBtnClass = "el-icon-star-on"
                }
                else
                    book.isCollected = false;
                if(book.likes==-1)
                    book.islike=true;
                else
                    book.islike=false;
                    //默认值关闭
            })
        })
      this.load();
    },
    redirectToAuthorBooks(author){          //将作者作为参数跳转到 找到作者的所有书籍 页面
      this.$router.push({
        path:'/searchResult',
        query:{params:JSON.stringify(author)}
      })
      location.reload()
    },
    load(){//分页模糊查询图书
      this.request.get("/",{           //更改后台接口
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          bookname:this.searchQuery
        }
      }).then(res=>{
        this.bookDetails=res.records           //根据后台数据更改
        this.total=res.total
        this.bookDetails.forEach(book => {          //遍历数组，为每个对象添加 isCollected 属性
          book.isCollected = false; // 默认值为 false
          book.collectBtnClass="el-icon-star-off"     //默认值关闭
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
    collectBtn(index){//收藏
      this.bookDetails[index].isCollected=!this.bookDetails[index].isCollected
      if(this.bookDetails[index].isCollected){
        this.$message({
          message: '收藏成功',
          type: 'success'
        });
        this.bookDetails[index].collectBtnClass="el-icon-star-on"
      }else{  //取消收藏
        this.$message({
          message: '取消成功',
          type: 'success'
        });
        this.bookDetails[index].collectBtnClass="el-icon-star-off"
      }
    },
    pushDetail(index){           //跳转路由函数
      // console.log(JSON.stringify(this.bookDetails[0]))
      router.push({
        path:'/bookdetails',
        //路由传递参数，将书的信息传递到书籍详情页
        query:{params:JSON.stringify(this.bookDetails[index])}
      })
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

<template>
  <div>
  <!-- 这里显示图书的详细信息-->
  <!--  1. 图书的封面；
        2. 图书的详细介绍（这里设置为用户提交图书的时候填写的内容）；
        3. 图书的下载量和收藏量；
        4. 关于图书本身的详细信息（即图书的名字、语言、还有文件大小、出版社、页数等等）；
        5. 下面是关于本书的留言评价，这些数据存放在数据库当中；
        6. 最下面是提供一个评论的入口，用户可以在这里提交自己的评价；
    -->
    <div class="parent-container">
      <el-container class="el-container">

      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 20px;margin-bottom: 20px">
          <el-breadcrumb-item :to="{ path: '/' }" style="color: red;">主页</el-breadcrumb-item>
          <el-breadcrumb-item>{{ bookDetails.title }}</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row class="book-card" :book_id="6138195">
          <el-col :xs="24" :sm="6" class="book-cover" >
            <div >
              <el-image :src="bookDetails.img" fit="cover" lazy style="height: 26pc;"/>
            </div>
            <el-button type="success" icon="el-icon-view" plain style="margin-left: 0px;margin-top: 5px;width: 300px" @click="previewBook">预览</el-button>
            <el-button type="primary" icon="el-icon-download" plain style="margin-left: 0px;margin-top: 5px;width: 300px">下载</el-button>
          </el-col>
          <el-col :xs="24" :sm="18" class="book-info">
            <el-row class="align-left">
              <h1 class="book-title" style="margin: 0;padding: 0">{{ bookDetails.title }}</h1>
            </el-row>
            <el-row class="align-left">
              <i class="book-author">
                <span @click="redirectToAuthorBooks(bookDetails.author)" title="找到该作者的所有书籍" class="color1" style="cursor: pointer">{{ bookDetails.author }}</span>
              </i>
            </el-row>
            <el-row class="align-left">
                <div style="font-size: 20px;display: inline-block">
<!--                    <i :class="collectBtnClass" @click="collectBtn" style="cursor: pointer;font-size: 25px;margin-right: 5px"></i>-->
                    <el-popover
                        placement="bottom"
                        width="230"
                        trigger="click">
                      <div style="font-size: 12px;text-align: center">-----------你有多喜欢这本书？-----------</div>
                      <div class="block" style="text-align: center">
                        <el-rate v-model="bookValue" allow-half="true"></el-rate>
                      </div>
                      <i class="el-icon-medal" slot="reference" style="cursor: pointer"></i>
                    </el-popover>
                  <el-tooltip effect="dark" content="图书评级/文件质量" placement="bottom">
                    <i>
                      <span class="book-rating-interest-score">{{ bookValue }}</span> /
                      <span class="book-rating-quality-score">4.0</span>
                    </i>
                  </el-tooltip>
                </div>

              <div style="display: inline-block" class="ml-5">
                <i class="el-icon-chat-dot-square" style="cursor: pointer;font-size: 25px;margin-right: 5px">
                  <span style="font-size: 20px;margin-right: 5px">1 comment</span>
                </i>
              </div>

              <el-tooltip :content="bookDetails.isCollected ? '取消收藏' : '收藏' ">
                <el-button class="book-add-to-list" :class="bookDetails.collectBtnClass" circle @click="collectBtn" />
              </el-tooltip>
              <el-tooltip content="感谢贡献者">
                <el-button class="book-thank-contributors" icon="el-icon-thumb" circle />
              </el-tooltip>
            </el-row>

            <el-row justify="space-between">
              <p class="grid-content" style="text-align: justify">{{bookDetails.description}}</p>
            </el-row>


            <el-row :gutter="20"  class="row-bg">
              <el-col :span="2" ><div class="grid-content col-content" >种类:</div></el-col>
              <el-col :span="6" ><div class="grid-content col-content" >{{bookDetails.category}}</div></el-col>
              <el-col :span="2" ><div class="grid-content col-content">年：</div></el-col>
              <el-col :span="6" ><div class="grid-content col-content">{{bookDetails.publishedDate}}</div></el-col>
            </el-row>
            <el-row :gutter="20"  class="row-bg" >
              <el-col :span="2"><div class="grid-content col-content">出版社:</div></el-col>
              <el-col :span="6"><div class="grid-content col-content">{{bookDetails.publisher}}</div></el-col>
              <el-col :span="2"><div class="grid-content col-content">语言：</div></el-col>
              <el-col :span="6"><div class="grid-content col-content">{{bookDetails.Language}}</div></el-col>
            </el-row>
            <el-row :gutter="20"  class="row-bg " >
              <el-col :span="2"><div class="grid-content col-content">ISBN:</div></el-col>
              <el-col :xs="8" :sm="6" :md="40" :lg="50" :xl="10"><div class="grid-content col-content">{{bookDetails.ISBN}}</div></el-col>
            </el-row>
          </el-col>
        </el-row>
<!--        <el-row type="flex" justify="left" >-->
<!--          <el-button type="primary" icon="el-icon-download" plain style="margin-left: 30px;width: 300px">下载</el-button>-->
<!--        </el-row>-->
      </el-main>
    </el-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "BookDetails",
  created() {
    const params = this.$route.query.params;
    if (params) {
      try {
        this.bookDetails = JSON.parse(params);
        console.log('数据是：', this.bookDetails);
      } catch (e) {
        console.error('解析参数出错：', e);
      }
    } else {
      console.warn('未找到参数');
    }
  },
  data() {
    return {
      bookDetails: {},
      collectBtnClass:"el-icon-star-off",
      bookValue:null
    }
  },
  methods:{
    collectBtn(){//收藏
      this.bookDetails.isCollected=!this.bookDetails.isCollected
      if(this.bookDetails.isCollected){
        this.$message({
          message: '收藏成功',
          type: 'success'
        });
        this.bookDetails.collectBtnClass="el-icon-star-on"
      }else{  //取消收藏
        this.$message({
          message: '取消成功',
          type: 'success'
        });
        this.bookDetails.collectBtnClass="el-icon-star-off"
      }
    },
    redirectToAuthorBooks(author){          //将作者作为参数跳转到 找到作者的所有书籍 页面
      this.$router.push({
        path:'/authorBooks',
        query:{params:JSON.stringify(author)}
      })
    },
    previewBook(){
      let url = 'http://localhost:9090/file/1de53068902f460b9c7b3bbe99719460.pdf';
      window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${url}`);
    }
  }
}
</script>

<style scoped>
.parent-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh; /* 设置高度以垂直居中容器 */
}

.el-container {
  transform: scale(0.8); /* 缩小容器 */
}

.book-title{
  font-size: 40px;
}

.align-left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 10px;
}


.ml-5{
  margin-left: 25px;
}

.book-rating-detail .book-comments-info {
  display: inline-block;
  padding-left: 24px;
  cursor: pointer;
}

.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  text-align: left;
  margin-left: 0px;
  border-radius: 4px;
  min-height: 36px;
}

.col-content{
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.row-bg {
  padding: 0px 0px;
  background-color: #f9fafc;
}

.color1 {
  font-size: 20px;
  color: #49afd0;
}
.book-rating-interest-score{
  color: #FAAD14;
}
.book-rating-quality-score{
  color: #5CB85C;
}
div {
  margin: 0;
  padding: 0;
}
</style>

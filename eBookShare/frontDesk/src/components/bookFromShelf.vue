<template>
  <div @click="goToPreview" v-if="bookInfo">
    <!--  下面的代码目的是为了展示位于书架上的一本图书！-->


    <el-tooltip placement="top" effect="light" style="height: 430px">
      <!--  鼠标移动提示部分-->
      <div slot="content">
        <span>{{ bookInfo.bookname }}</span>
        <div class="bottom clearfix">
          <span>{{ bookInfo.description}}</span>
          <el-button type="success" class="button">下载</el-button>
        </div>
      </div>

      <!--  图片显示部分-->
      <el-card style="height: 455px">
        <div style="position: relative; width: 100%; height: 100%;">
          <img :src="bookInfo.coverimage" class="image" style="height: 370px; width: 250px">
        </div>
        <div style="margin-top: 2px; margin-bottom: 2px">
          {{ bookInfo.bookname }}
        </div>
      </el-card>
    </el-tooltip>

  </div>
</template>

<script>
export default {
  name: "bookFromShelf",
  props :{
    bookInfo: {}
  },
  data(){
    // 图片显示问题：
    // 1. 使用绝对路径比较好，将要显示的图片信息存放在public下的images文件夹中
    // 我们就可以直接使用bookView: "/images/books.png"来进行访问
    // 2. 使用相对路径访问图片信息，该种情况需要将图片信息存放在assets/images文件夹中
    // 然后我们就可以使用bookView: require("../assets/images/books.png")来进行访问
    return{
      // 图书信息
      bookDetails: {
        // 绝对路径访问图片信息:
        bookView: "/images/books.png",

        testView: "http://124.71.166.37:9091/file/bookpic/95682c942b8b475bb90ec9f51bd57019.png"
        // 相对路径访问图片信息:
        //bookView: require("../assets/images/books.png")
      }
    }
  },
  created() {
    console.log(this.bookInfo);
  },
  methods: {
    // 如果点击图书封面的话，会去在线预览电子图书
    goToPreview(){
      let url = 'http://124.71.166.37:9091/file/5c50f98e6850407fa82991b6e248915a.pdf';
      window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${url}`);
    }
  }
}
</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

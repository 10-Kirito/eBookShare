<template>
  <div @click="goToPreview">
    <!--  下面的代码目的是为了展示位于书架上的一本图书！-->


    <el-tooltip placement="top" effect="light">
      <!--  鼠标移动提示部分-->
      <div slot="content">
        <span>{{ bookDetails.bookName }}</span>
        <div class="bottom clearfix">
          <time class="time">{{ new Date() }}</time>
          <el-button type="success" class="button">下载</el-button>
        </div>
      </div>

      <!--  图片显示部分-->
      <el-card style="height: 455px">
        <img :src="bookDetails.bookView" class="image">
        <div style="margin-top: 2px; margin-bottom: 2px">
          {{ bookDetails.bookName }}
        </div>
      </el-card>
    </el-tooltip>

  </div>
</template>

<script>
export default {
  name: "bookFromShelf",
  props :{
    bookInfo:{}
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
        bookName: "代码简洁之道",
        // 绝对路径访问图片信息:
        bookView: "/images/books.png"
        // 相对路径访问图片信息:
        //bookView: require("../assets/images/books.png")
      }
    }
  },
  methods: {
    // 如果点击图书封面的话，会去在线预览电子图书
    goToPreview(){
      let url = 'http://localhost:9090/file/1de53068902f460b9c7b3bbe99719460.pdf';
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

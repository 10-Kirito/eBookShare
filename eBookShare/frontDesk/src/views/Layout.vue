<template>
  <div style="margin: -8px">
    <el-container>
      <!-- 首页头部-->
      <el-header >
        <Header></Header>
      </el-header>

      <!-- 首页主体部分-->
      <el-main>
        <router-view></router-view>
      </el-main>

      <!--  首页下半部分-->
      <el-footer height="100px">
        <div>
          <h2>@上海大学电子图书分享平台</h2>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import bookDetails from "@/views/Books/BookDetails.vue";
import router from "@/router/index.js"
import Header from "@/components/Header.vue";
export default {
  name: "Layout",
  components: {
    Header
  }
}

</script>

<style scoped>

.el-header{
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

.el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

</style>


<template>
  <div style="margin: -8px">
    <el-container>
      <!-- 首页头部-->
      <el-header >
        <Header :user="user"></Header>
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
  },
  data() {
    return{
      user: {}
    }
  },
  created() {
    //从后台获取新数据
    this.getUser()
  },
  methods:{
    getUser(){
      //从后台获取数据
        let username = localStorage.getItem("loguserinfo")?JSON.parse(localStorage.getItem("loguserinfo")).username:""
        if(username!==""){
          console.log("success")
        this.request.get("http://localhost:9091/users/" + username).then(res =>{
          //重新复制后台的最新数据
          this.user = res.data
        })
      }
    }
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


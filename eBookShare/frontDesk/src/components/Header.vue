<template>
  <div>
    <el-header>
      <!--   首页头部   -->
      <el-col :span="12" :offset="1" align="left">
        <div class="grid-content bg-purple">
          <router-link to="/" style="text-decoration: none">{{ pageTitle }}</router-link>
        </div>
      </el-col>
      <el-row type="flex" justify="end">
        <el-col :span="8"><router-link to="/" style="text-decoration: none">首页</router-link></el-col>
        <el-col :span="8"><router-link to="/home/bookshelf"  style="text-decoration: none">个人书架</router-link></el-col>
        <el-col :span="8"><router-link to="/"  style="text-decoration: none">登录</router-link></el-col>
      </el-row>
    </el-header>
  </div>

</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      pageTitle: '上海大学电子图书分享平台'
    }
  }
}
</script>

<style scoped>

</style>

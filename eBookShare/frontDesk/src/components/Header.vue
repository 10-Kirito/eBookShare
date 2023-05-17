<template>
  <div>
    <el-header>
      <!--   首页头部   -->
      <el-col :span="12" :offset="1" align="left">
        <div class="grid-content bg-purple">
          <router-link to="/" style="text-decoration: none">{{ pageTitle }}</router-link>

<!--        <el-button @click="test">a按钮</el-button>-->
        </div>
      </el-col>
      <el-row type="flex" justify="end">
        <el-col :span="8"><router-link to="/" style="text-decoration: none">首页</router-link></el-col>
        <el-col :span="8"><router-link to="/home/bookshelf"  style="text-decoration: none">个人书架</router-link></el-col>

        <el-col :span="8">
          <div v-show="this.user.username==null">
            <router-link to="/login"  style="text-decoration: none">登录</router-link>
          </div>

          <div v-show="this.user.username!=null">
            <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
              <div style="display: inline-block">
                <!--      头像功能-->
                <img :src="user.avatarurl" alt="" style="width: 30px;border-radius: 50%;position: relative;top:10px;right:5px">
                <span>{{ user.username }}</span>
                <!--      这里的参数是后台.controller.dto中admindto的内容-->
                <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
              </div>
              <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
                <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                  <router-link to="/person" style="text-decoration: none">个人信息</router-link>
                </el-dropdown-item>
                <span style="text-decoration: none" @click="logout">
                  <el-dropdown-item style="font-size: 14px;padding: 5px 0">退出</el-dropdown-item>
                </span>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-col>

      </el-row>




    </el-header>
  </div>

</template>

<script>
export default {
  name: "Header",
  props:{
    user: Object
  },
  data(){
    return{
      pageTitle: '上海大学电子图书分享平台'
    }
  },
  methods:{
    logout(){
      localStorage.removeItem("loguserinfo")
      this.$router.push("/home")
      location.reload()
      this.$message.success("退出成功")
    },
    test(){
      console.log("123--")
      console.log(this.user.username!=null)
    }
  }
}
</script>

<style scoped>

</style>

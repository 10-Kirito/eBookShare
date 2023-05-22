<template>
  <div>
    <el-header class="header-with-background">
      <!--   首页头部   -->
      <el-col :span="12" :offset="1" align="left">
        <div class="grid-content bg-purple">
          <router-link to="/" style="text-decoration: none">{{ pageTitle }}</router-link>
<!--        <el-button @click="test">a按钮</el-button>-->
        </div>
      </el-col>

      <el-row type="flex" justify="end">
        <el-col :span="8"><router-link to="/" style="text-decoration: none">首页</router-link></el-col>

        <el-col :span="8">
          <el-dropdown style="width: 150px; cursor: pointer; text-align: center">
            <div style="display: inline-block">
              <span>书架</span>
              <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
              <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                <router-link to="/bookshelf"  style="text-decoration: none">个人书架</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                <router-link to="/favourshelf"  style="text-decoration: none">收藏书架</router-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>


        <el-col :span="8">
          <div v-show="this.user.username==null">
            <router-link to="/login"  style="text-decoration: none">登录</router-link>
          </div>

          <div v-show="this.user.username!=null">
            <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
              <div style="display: inline-block">
                <img :src="user.avatarurl" style="width: 30px;border-radius: 50%;position: relative;top:10px;right:5px">
                <span>{{ user.username }}</span>
                <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
              </div>
              <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
                <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                  <router-link to="/person" style="text-decoration: none">个人信息</router-link>
                </el-dropdown-item>
                <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                  <router-link to="/uploadbook" style="text-decoration: none">上传书籍</router-link>
                </el-dropdown-item>
                <span style="text-decoration: none" @click="logout">
                  <el-dropdown-item style="font-size: 14px;padding: 5px 0">退出</el-dropdown-item>
                </span>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-col>
        <div v-show="this.user.username!=null" style="width: 300px;">
            <div class="grid-content bg-purple" style="height: 60px;display: flex; flex-direction: column">
              <div style="text-decoration: none;height: 30px;margin-top: -10px;font-size: 13px">积分:{{ user.points }} </div>
              <div style="text-decoration: none;height: 30px;margin-top: -8px;font-size: 13px">免费下载次数:{{ user.points }} </div>
            </div>
        </div>
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
      pageTitle: '上海大学电子图书分享平台',
      imagesurl: require("../assets/images/cutcamera.png")
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
.header-with-background {
  background-image: url('~@/assets/images/whitehead.jpg');
  background-size: cover;
}
</style>

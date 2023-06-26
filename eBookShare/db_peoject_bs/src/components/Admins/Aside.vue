<template>
  <el-menu :default-openeds="['1', '3']" style = "min-height : 100%; overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
           @select="handleSelect"
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">

      <b style="color: white" v-show="logoTextShow">E-Book后台管理系统</b>
    </div>
    <el-menu-item index="/admins/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理 </span>
      </template>
      <el-menu-item index="/admins/ebookmanage">
          <i class="el-icon-document"></i>
          <span slot="title">书籍管理</span>
      </el-menu-item>
<!--    后面针对其他角色需要更改路由  -->
      <el-menu-item index="/admins/ebookrecover">
        <i class="el-icon-document"></i>
        <span slot="title">书籍恢复</span>
      </el-menu-item>
      <el-menu-item index="/admins/usermanage">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/admins/ebookAudit">
        <i class="el-icon-user"></i>
        <span slot="title">书籍审核</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title"><i class="el-icon-menu"></i>
        <span slot="title">商城管理</span>
      </template>
      <el-menu-item index="/admins/products">
        <template >
          <i class="el-icon-menu"></i>
          <span slot="title">商品管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/admins/lunbo">
        <template >
          <i class="el-icon-menu"></i>
          <span slot="title">轮播图管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/admins/orders">
        <template >
          <i class="el-icon-menu"></i>
          <span slot="title">订单管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  methods:{
    handleSelect(){
      // console.log(this.$router.options.routes)
      //this.$router.history.fullPath.split("/")
    }
  }
}
</script>

<style scoped>

</style>
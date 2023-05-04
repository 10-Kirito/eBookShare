import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

import Index from "@/components/views/Index.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: "/Login",
  },

  {
    path: '/admins',
    component: () => import("../views/Admins/AdminsManage.vue"),
    redirect: "/admins/home",
    children:[
      // {path: 'selecourse', name: '选课' , component: () => import('../views/student/StudentSeleceCourse.vue')},
      {path: 'home', name: '用户管理' , component: () => import('../views/Admins/AdminsHome.vue')},
      {path:'ebookmanage', name: '电子书籍管理', component:() => import('../views/Admins/EBookManage.vue')},
      {path:'ebookrecover', name: '书籍恢复', component:() => import('../views/Admins/EBookRecover.vue')},
      {path:'ebookupload', name: '书籍上传', component:() => import('../views/Admins/EBookUpload.vue')},
      {path:'usermanage', name: '用户管理', component:() => import('../views/Admins/UserManage.vue')},
      {path:'ebookupAndAudit', name: '用户上传与审核', component:() => import('../views/Admins/EbookUploadAndAudit.vue')},
      {path:'ebookAudit', name: '书籍审核', component:() => import('../views/Admins/EBookAudit.vue')},
      // {path:'classtable', name: '课表查询', component:() => import('../views/student/StudentClassTable.vue')}


    ],

  }
  , {
    path:'/login',
    name: 'Login',
    component:() => import('../views/Login.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  //console.log(from)
  localStorage.setItem("currentPathName",from.name)  //设置当前路由名称,为了在header组件中使用
  store.commit("setPath")  //触发store数据更新
  next()   //路由放行
})


export default router

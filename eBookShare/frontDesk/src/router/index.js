import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/login',
    name: '登录',
    component: () => import('@/views/Login/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Layout.vue'),
    children: [
      {
        path: '',
        name:'defaultPage',
        component: () => import('@/views/HomePage.vue')
      },
      {
        path: '/bookshelf',
        name:'个人书架',
        component: () => import('@/views/BookShelf.vue')
      },{
        path: '/favourshelf',
        name:'收藏书架',
        component: () => import('@/views/FavourShelf.vue')
      },
      ,{
        path: '/pointsmall',
        name:'积分商城',
        component: () => import('@/views/PointsMall.vue')
      },
      {
        path: '/bookdetails',
        name: '图书详情',
        component: () => import('@/views/Books/BookDetails.vue')
      },
      {
        path: '/searchResult',
        name: '搜索结果',
        component: () => import('@/views/Books/SearchResult.vue')
      },
      {
        path: '/authorBooks',
        name:'作者书籍',
        component:()=>import('@/views/Books/AuthorBooks.vue')
      }
      ,
      {
        path: '/person',
        name:'个人信息',
        component:()=>import('@/views/person.vue')
      },
      {
        path: '/uploadbook',
        name:'上传书籍',
        component:()=>import('@/views/UploadBook.vue')
      }
    ]
  },
  {
    path: '/shop',
    name: 'Shop',
    component: () => import('@/views/shop/Front.vue'),
    redirect:"/shop/home",
    children: [
      {path: 'home',name:'首页',component:()=>import('../views/shop/Home.vue')},
      {path: 'detail', name: '商品详情',component: () => import('../views/shop/Detail.vue')},
      {path: 'cart', name: '购物车',component: () => import('../views/shop/Cart.vue')},
      {path: 'orders', name: '我的订单',component: () => import('../views/shop/Orders.vue')},
    ]
  },
  {
    path: '/pdf',
    name: 'pdf',
    component: () => import('@/pdf/index.vue')
  },
  {
    path: '/test1',
    name: 'test1',
    component: () => import('@/views/BookShelf.vue')
  },
  {
    path: '/test2',
    name: 'test2',
    component: () => import('@/views/Login/Login.vue')
  },
  {
    path: '/comment',
    name: 'comment',
    component: () => import('@/components/comments.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

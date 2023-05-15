import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
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
        path: 'bookshelf',
        name:'个人书架',
        component: () => import('@/views/BookShelf.vue')
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
    ]
  },


  {
    path: '/test',
    name: 'test',
    component: () => import('@/pdf/BookDetails.vue')
  },
  {
    path: '/test1',
    name: 'test1',
    component: () => import('@/views/BookShelf.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

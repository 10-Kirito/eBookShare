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
        name:'bookshelf',
        component: () => import('@/views/BookShelf.vue')
      }
    ]
  },
  {
    path: '/bookdetails',
    name: 'details',
    component: () => import('@/views/Books/BookDetails.vue')
  },
  {
    path: '/searchResult',
    name: 'details',
    component: () => import('@/views/Books/SearchResult.vue')
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

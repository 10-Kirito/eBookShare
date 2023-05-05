import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'homed',
    redirect:'/home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Layout.vue')
  },
  {
    path: '/bookdetails',
    name: 'details',
    component: () => import('@/views/Books/BookDetails.vue')
  },
  {
    path: '/test',
    name: 'test',
    component: () => import('@/pdf/BookDetails.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/group',
    children:[
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/User")
      },
        {
            path: 'manager',
            name: 'Manager',
            component: () => import("@/views/Manager")
        },
        {
            path: 'science',
            name: 'science',
            component: () => import("@/views/Science")
        },
      {
        path: 'project',
        name: 'Project',
        component: () => import("@/views/Project")
      },
      {
        path: 'group',
        name: 'Group',
        component: () => import("@/views/Group")
      },
        {
            path: '/person',
            name: 'Person',
            component: () => import("@/views/Person")
        },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },

    {
        path: '/register',
        name: 'Register',
        component:
            () => import("../views/Register.vue")
    },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

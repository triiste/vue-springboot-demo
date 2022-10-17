import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/login',
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
        {
            path: 'joinproject',
            name: 'joinproject',
            component: () => import("@/views/Joinproject")
        },
        {
            path: 'hostproject',
            name: 'hostproject',
            component: () => import("@/views/Hostproject")
        },
        {
            path: 'oneitemgroup',
            name: 'oneitemgroup',
            component: () => import("@/views/Oneitemgroup")
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

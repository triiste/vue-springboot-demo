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
        {
            path: 'officeperson',
            name: 'officeperson',
            component: () => import("@/views/Officeperson")
        },
        {
            path: 'oneofficegroup',
            name: 'oneofficegroup',
            component: () => import("@/views/Oneofficegroup")
        },
        {
            path: 'officehostproject',
            name: 'officehostproject',
            component: () => import("@/views/Officehostproject")
        },
        {
            path: 'home',
            name: 'home',
            component: () => import("@/views/Home")
        },
        {
            path: 'officejoinproject',
            name: 'officejoinproject',
            component: () => import("@/views/Officejoinproject")
        },
        {
            path: 'project1',
            name: 'project1',
            component: () => import("@/views/Project1")
        },
        {
            path: 'project2',
            name: 'project2',
            component: () => import("@/views/Project2")
        },
        {
            path: 'project3',
            name: 'project3',
            component: () => import("@/views/Project3")
        },
        {
            path: 'project4',
            name: 'project4',
            component: () => import("@/views/Project4")
        },
        {
            path: 'personEcharts',
            name: 'personEcharts',
            component: () => import("@/views/PersonEcharts")
        },
        {
            path: 'officeuser',
            name: 'officeuser',
            component: () => import("@/views/Officeuser")
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

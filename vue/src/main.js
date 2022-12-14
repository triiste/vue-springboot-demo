import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import XLSX from "xlsx"
import FileSaver from 'file-saver'

import '@/assets/css/global.css'
import {ArrowDown} from '@element-plus/icons-vue'

createApp(App).use(store).use(router).use(ElementPlus, {locale: zhCn,}).component('arrow-down', ArrowDown).mount('#app')
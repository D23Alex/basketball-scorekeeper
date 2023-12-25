import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  mode: 'history',
  base: '/basketball-scorekeeper/',
  baseUrl: '/basketball-scorekeeper/',
  publicPath: '/basketball-scorekeeper/',
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})

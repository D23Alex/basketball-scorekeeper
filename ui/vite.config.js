import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  base: '/basketball-scorekeeper/',
  baseUrl: '/basketball-scorekeeper/',
  publicPath: '/basketball-scorekeeper/',
  plugins: [vue()],
  server: {
    host: 'frontend',
    port: 3000
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})

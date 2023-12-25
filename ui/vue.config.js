module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
        ? '/your-github-repo/' // note the trailing slash
        : '/',
}

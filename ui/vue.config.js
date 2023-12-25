module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
        ? '/basketball-scorekeeper/' // note the trailing slash
        : '/',
}

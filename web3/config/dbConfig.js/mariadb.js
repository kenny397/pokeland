var mariadb = require('mariadb'); 
const pool = mariadb.createPool({
    host: '127.0.0.1',    
    port: 3306,   
    user: 'root',    
    password: 'ssafy',   
    connectionLimit: 5,   
    database:"ssafy_nft_web" 
});

module.exports = conn;
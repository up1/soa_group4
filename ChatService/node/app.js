var utf8 = require('utf8');
var express = require('express')
var app = express();
var path = require('path');
var request = require('request');
var port = 9012;

var server = app.listen(port, function() {
    console.log('Listening on port: ' + port);
});

var mysql = require('mysql');
var pool  = mysql.createPool({
  host     : '128.199.243.49',
  port     : '3306',
  user     : 'root',
  password : 'root',
  database : 'chat'
});

var clients = {};

var io = require('socket.io').listen(server);

// เมื่อมี client เข้ามาเชื่อมต่อให้ทำอะไร?
io.on('connection', function(socket) {
    // แสดงข้อความ "a user connected" ออกมาทาง console
    console.log('a user connected');
    socket.on('subscribe', function(data) {
        // แสดงข้อมูลที่ได้ ออกมาทาง console
        socket.join(data.room);
        if (clients[socket.id] == undefined) {
          clients[socket.id] = {username: data.username};
        }
        console.log(clients[socket.id]);
        console.log("Socket now in rooms", socket.rooms);

        var users = io.sockets.adapter.rooms[data.room].sockets;
        var length = io.sockets.adapter.rooms[data.room].length;
        var count = 0;
        var read = 1;
        for (var user in users) {
          if (users.hasOwnProperty(user)) {
            console.log(clients[user].username,data.username);
            if(clients[user].username === data.username){
              count++;
            }
          }
        }

        if (length === 2) {
            console.log("*********************************" + length);
            var requestData = {
                "user_id": data.username,
            	"channel": data.room,
            	"time": new Date().getTime(),
                "status": 1
            }
            request.put({
                url: "http://localhost:9007/chat",
                json: true,
                headers: {
                    "content-type": "application/json"
                },
                body: requestData
            }, function(error, response, body){
                if (!error) {
                    console.log(body);
                } else {
                    console.error(error);
                }
            });
        }
    });
    socket.on('unsubscribe', function(data) {
        socket.leave(data.room);
    });
    socket.on('send message', function(data) {
        if (data.room !== undefined && data.reply != undefined) {
            var users = io.sockets.adapter.rooms[data.room].sockets;
            var length = io.sockets.adapter.rooms[data.room].length;
            var count = 0;
            var read = 1;
            for (var user in users) {
              if (users.hasOwnProperty(user)) {
                console.log(clients[user].username,data.user_id);
                if(clients[user].username === data.user_id){
                  count++;
                }
              }
            }
            if (count === length) {
              read = 0;
            }

            if (length === 1){
                console.log("AVAVAVAVAVAVAVAVAVAVAVAVAVAVAVAAV:   " + length + "," + data.room);
                var requestData = {
                    "user_id": data.user_id,
                	"channel": data.room,
                	"time": new Date().getTime(),
                    "status": 0
                }
                request.put({
                    url: "http://localhost:9007/chat",
                    json: true,
                    headers: {
                        "content-type": "application/json"
                    },
                    body: requestData
                }, function(error, response, body){
                    if (!error) {
                        console.log(body);
                    } else {
                        console.error(error);
                    }
                });
            }

            pool.getConnection(function(err, connection) {
                if(err) {
                    console.log(err);
                    callback(true);
                    return;
                }
                var sql = 'INSERT INTO conversation_reply (user_id, reply, time, channel) VALUES ('
                                  + data.user_id +','
                                  + '"' + data.reply + '"' +','
                                  + new Date().getTime() + ','
                                  + '"' + data.room + '")'
                connection.query(sql, [], function(err) {
                    connection.release();
                    if(err) {
                        console.log(err);
                        callback(true);
                        return;
                    }
                });
                io.sockets.in(data.room).emit('conversation private post', data);
            });
        }
    });
    socket.on('disconnect', function(){
        if (clients[socket.id] !== undefined) {
          delete clients[socket.id];
          console.log(socket.id,"disconnected");
        }
    });
});

```
server {
        listen 80 default_server;
        listen [::]:80 default_server;

        server_name i5a603.p.ssafy.io;

        return 301 https://$server_name$request_uri;

        index index.html index.htm;
}

server {
        listen 443 ssl;
        listen [::]:443 ssl;

        server_name i5a603.p.ssafy.io;

        ssl_certificate /etc/letsencrypt/live/i5a603.p.ssafy.io/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/i5a603.p.ssafy.io/privkey.pem;

        root /home/ubuntu/S05P13A603/frontend/dist;
        index index.html index.htm;

        location / {
                try_files $uri $uri/ /index.html;

                proxy_buffer_size       128k;
                proxy_buffers           4 256k;
                proxy_busy_buffers_size 256k;


        }

        location /api {
                proxy_pass http://localhost:8081/api;
                proxy_redirect off;
                charset utf-8;

                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header X-NginX-Proxy true;


                proxy_buffer_size       128k;
                proxy_buffers           4 256k;
                proxy_busy_buffers_size 256k;


        }
}
```


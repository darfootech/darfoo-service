ssh root@115.29.238.12   'cd /home/darfoodatabase; \
                          mysqldump --extended-insert=FALSE --complete-insert=TRUE -uroot -p darfoo > darfoo.sql; \
                          git commit -a -m"backupdatabase";\
                          git push'

cd /Users/zjh/Documents/darfoo/servicedatabasebackup/darfoodatabase; git pull origin master

git rm -rf --cached pathToFileIgnore

git commit -m "Stop tracking application.properties"

git filter-branch --force --index-filter 'git rm --cached --ignore-unmatch src/main/resources/application.properties' --prune-empty --tag-name-filter cat -- --all

git push origin --force --all

cho nay conflic ne

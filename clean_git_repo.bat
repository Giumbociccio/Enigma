@echo off
echo -------------------------------------
echo Rimozione file Eclipse e Maven da Git...
echo -------------------------------------

REM Rimuove i file tracciati da Git che ora sono ignorati
git rm --cached .classpath
git rm --cached .project
git rm --cached -r .settings
git rm --cached -r target

echo -------------------------------------
echo Commit delle modifiche...
echo -------------------------------------

git add .gitignore
git commit -m "Pulizia repo: rimosso metadata Eclipse e cartella target, aggiunto .gitignore"

echo -------------------------------------
echo Fatto! Ora puoi fare push con:
echo     git push
echo -------------------------------------
pause

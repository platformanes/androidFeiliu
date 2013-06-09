@echo off
::转到当前盘符
%~d0
::打开当前目录
cd %~dp0
::你做的主JAR包的路径
set MainJar=flane.jar
::第三方JAR包的路径
set jar1=androidsdklibrary.jar
set jar2=gamehelper.jar
set jar3=oauth20.jar
set jar4=oauth20-api.jar
set jar5=sinaweibosso.jar
set jar6=tencent_openapi.jar
set jar7=alipay_msp.jar
::第三方JAR包顶级包名称
set packageName1=com
set packageName2=org
set packageName3=android
echo =========== start combin ==============
::解压第三方包
jar -xf %jar1%
jar -xf %jar2%
jar -xf %jar3%
jar -xf %jar4%
jar -xf %jar5%
jar -xf %jar6%
jar -xf %jar7%
::合并主JAR包
jar -uf %MainJar% %packageName1% 
::如果还有别的顶级包可以接着合并，例如：
jar -uf %MainJar% %packageName2%
jar -uf %MainJar% %packageName3%
echo =========== over ==============
echo 再点一下就结束了--小Q
pause
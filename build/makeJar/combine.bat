@echo off
::ת����ǰ�̷�
%~d0
::�򿪵�ǰĿ¼
cd %~dp0
::��������JAR����·��
set MainJar=flane.jar
::������JAR����·��
set jar1=androidsdklibrary.jar
set jar2=gamehelper.jar
set jar3=oauth20.jar
set jar4=oauth20-api.jar
set jar5=sinaweibosso.jar
set jar6=tencent_openapi.jar
set jar7=alipay_msp.jar
::������JAR������������
set packageName1=com
set packageName2=org
set packageName3=android
echo =========== start combin ==============
::��ѹ��������
jar -xf %jar1%
jar -xf %jar2%
jar -xf %jar3%
jar -xf %jar4%
jar -xf %jar5%
jar -xf %jar6%
jar -xf %jar7%
::�ϲ���JAR��
jar -uf %MainJar% %packageName1% 
::������б�Ķ��������Խ��źϲ������磺
jar -uf %MainJar% %packageName2%
jar -uf %MainJar% %packageName3%
echo =========== over ==============
echo �ٵ�һ�¾ͽ�����--СQ
pause
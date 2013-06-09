androidFeiliu
=============

飞流android平台ANE
* 此ANE可提供aser直接用，无需涉及java端。
* 保留ANE原件 和ANE源码   AS端源码和SWC文件
* 把涉及SDK的jar 文档等去掉（已经和官方沟通 但官方明确不准分享SDK相关文档）
* 若需要自己写  接SDK的时候 把官方提供的SDK相应文件导入库即可
* DEMO的相关参数 需要配置正确的才可以正常使用
* 温馨提示：这个SDK配置很复杂 请详细看SDK文档！！！

## 资源

* 官方网站 [feiliu](http://www.feiliu.com/)
* 未开放下载地址

## 编译方法
* A复制 SDK的JAR到`build/makeJar` ，运行`combine.bat `合并jar
* B 复制A中得到的jar到`build/buildANE/Android-ARM`，运行`ane_packer.bat`得到ANE


## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成
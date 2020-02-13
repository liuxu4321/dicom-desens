# DICOM文件脱敏工具

无聊，隔离14天，在家工作。

## 外观

![image-20200210120613612](https://raw.githubusercontent.com/liuxu4321/dicom-desens/master/image-20200210120613612.png)

## 运行

这是一个Java GUI程序，发布的是一个jar文件。

可以使用java命令运行：

```java
java -jar dicomdesens.jar
```

亦可以将上面命令保存成bat文件。

## 工具用途

该工具是用来将DICOM文件进行脱敏操作的。使用该工具可以达到对DICOM文件进行一级脱敏，即将患者姓名，医院名称以及医院地址进行移除或替换。

## 使用方法

### 处理脱敏值

脱敏值共有三个

#### 患者信息
- patientName[0010,0010]
- patientBirthDate[0010,0030]
- patientSex[0010,0040]
- patientAge[0010,1010]

#### 医院信息
- institutionName[0008,0080]
- institutionAddress[0008,0081]

#### 检查信息
- studyID[0020,0010]
- accessionNumber[0008,0050]
- operatorName[0008,1070]

匿名化，字符内容内容替换为"**",数值型替换为"0",日期型替换为"00000000"。

删除，即将该字段在DICOM原始文件中进行删除。

### 文件夹选项

- 源文件夹：DICOM原始文件存放的位置
- 目标文件夹：脱敏后的DICOM文件存放的位置
- 原位置替换：将源文件直接脱敏

全部保留目录结构。

### 点击开始处理

点击处理后，需要迭代查找文件夹下所有文件，这里要稍等一会。处理速度还是很快的。

处理完成后在处理记录中输出了信息。

处理成功的文件在末尾有**OK**输出。另一个常见的信息时**notdicom**，表示该文件非标准DICOM文件。

- 文件总数：文件夹下文件总数
- 处理总数：没什么好说的了

## 限制

Java JDK1.8 运行环境


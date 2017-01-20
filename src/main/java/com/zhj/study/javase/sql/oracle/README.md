Oracle数据库字段长度相关知识
	参数介绍：
		NLS_LENGTH_SEMANTICS
			BYTE	字段长度单位为字节
			CHAR	字段长度单位为字符
		
		NLS_CHARACTERSET
			ZHS16GBK	数据库字符集为GBK
			...			其他字符集
	
	结论：
		1.	字段类型为nchar(5)、nvarchar(5)、nvarchar2(5)时，表示该字段可以存储5个字符。n开头的字段类型不受[NLS_LENGTH_SEMANTICS]值的影响。
		2.	字段类型为char(5)、varchar(5)、varchar2(5)时
			2.1	当[NLS_LENGTH_SEMANTICS]为CHAR时，表示该字段可以存储5个字符。
			2.2	当[NLS_LENGTH_SEMANTICS]为BYTE时，表示该字段可以存储5个字节。所以会受数据库字符编码方案[NLS_CHARACTERSET]影响，导致限制用户输入的字符长度和字段长度不匹配。
	
	解决方案：
		1.	修改Oracle数据库字段长度单位为CHAR。
		2.	修改字段类型为nvarchar2。
		3.	字段长度单位为BYTE，数据库采用varchar，然后字段长度调大一些，比如限制用户输入字符长度的三倍（UTF-8时）。
		
	扩展知识：
		1.	根据Oracle的定义，NLS_LENGTH_SEMANTICS参数可以在三个层次生效：数据库级、实例级、会话级，并且生效的优先顺序为会话级>实例级>数据库级。在会话级别修改该参数，不会影响已经存在的表中的字段长度单位，只会影响在该会话中创建的表的字段长度单位
		2.	char和varchar[varchar2]的区别：
				char定长，varchar[varchar2]变长。
				char效率高，varchar[varchar2]节省空间。
		3.	varchar和varchar2的区别：
				varchar2 空串=null，varchar 空串=空串，不能用is null查到【官方建议使用varchar2，保证兼容性】
		4.	mysql5之后，varchar、char的长度单位默认为字符，之前默认为字节
	
	参考资料：
		http://www.cnblogs.com/kerrycode/archive/2013/12/10/3466993.html
		http://blog.csdn.net/mrluoe/article/details/7264379
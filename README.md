### 建庫語法
```text
create database coin_desk collate 'utf8mb4_general_ci';
```

### 建表語法
```text
use coin_desk;
CREATE TABLE `coin2` (
  `chart_name` varchar(32) NOT NULL COMMENT '圖表名稱',
  `code` varchar(32) NOT NULL COMMENT '代碼',
  `symbol` varchar(32) NOT NULL COMMENT '標示',
  `description` varchar(255) DEFAULT NULL COMMENT '備註',
  `rate` decimal(19,2) NOT NULL COMMENT '匯率',
  `update_time` datetime DEFAULT NULL COMMENT '更新時間',
  PRIMARY KEY (`chart_name`,`code`)
) COMMENT '幣種表'
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```



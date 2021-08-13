# 配方格式

## 炒菜配方（Cooking Recipes）

### 有序炒菜配方（rdered Cooking Recipes，有加入食材先后顺序的配方类型）

```json
{
    "type": "sinocraft:cooking_ordered",	// 表示有序炒菜配方。
    "tool": "sinocraft:spatula",	// 必须的烹饪工具，例如这里的锅铲。
    "order": [	// 食材加入顺序。
        "A", 
        "A", 
        "B", 
        "C"
    ], 
    "ingredients": {	// 食材表。
        "A": {
            "item": "minecraft:carrot",	// 胡萝卜是第一和第二个所需要的食材。
            "interval": {	// 投入本食材后投入下一食材的间隔时间。（可选，默认不限制；单位：tick）
                "min": 20, 
                "max": 200
            }, 
        }, 
        "B": {
            "item": "minecraft:potato"
        }, 
        "C": {
            "tag": "sinocraft:radishes"	// 以物品标签（Tag）来选择允许的物品也是可以的。
        }
    },
    "temperature": {	// 制作过程中控制的温度，温度过低无法开始，温度越高其它所有的时间都越短。（单位：摄氏度）
    	"max": 150, 	// min 和 max 在对应块中的顺序没有影响。
    	"min": 100
    }, 
    "duration": 600, 	// 最后一个食材投入后，需要等待的时间。（单位：tick）
    "term": {	// 等待时间过后，能够把成品取出而不变成灰烬的时间。（单位：tick）
    	"min": 200,
        "max": 300	// 过了这个时间后，无法取出成品而只能取出灰烬。
	}, 
    "result": {
        "tool": "sinocraft:dish", 	// 盛装工具，这里是盘子。（可选，默认即空手就可以取出）
        "output": {
            "item": "sinocraft:heroes_assemble",	// 成品菜肴。
            "count": 5	// 一锅炒出来的菜能装满多少个容器，或者说可以取出多少次。
        }, 
        "charred": "sinocraft:charred_dishes"	// 菜肴烧焦以后，可取出的成品。（可选，默认为烧焦的饭菜）
    }
}
```

### 无序炒菜配方（Cooking Recipes，无加入食材先后顺序）

```json
{
    "type": "sinocraft:cooking",	// 表示无序炒菜配方。
    "tool": "sinocraft:spatula",	// 必须的烹饪工具，例如这里的锅铲。
    "ingredients": [	// 食材列表，注意这里是列表形式。
        {
            "item": "minecraft:carrot"
        }, 
        {
            "item": "minecraft:potato"
        }, 
        {
            "tag": "sinocraft:radishes"	// 以物品标签（Tag）来选择允许的物品也是可以的。
        }
    ],
    "temperature": {	// 制作过程中控制的温度，温度过低无法开始，温度越高其它所有的时间都越短。（单位：摄氏度）
    	"max": 600, 	// min 和 max 在对应块中的顺序没有影响。
    	"min": 100
    }, 
    "duration": 600, 	// 所有食材投入齐全后，需要等待的时间。（单位：tick）
    "term": {	// 等待时间过后，能够把成品取出而不变成灰烬的时间。（单位：tick）
    	"min": 200,
        "max": 300	// 过了这个时间后，无法取出成品而只能取出灰烬。
	}, 
    "result": {
        "tool": "sinocraft:dish", 	// 盛装工具，这里是盘子。（可选，默认即空手就可以取出）
        "output": {
            "item": "sinocraft:heroes_assemble",	// 成品菜肴。
            "count": 5	// 一锅炒出来的菜能装满多少个容器，或者说可以取出多少次。
        }, 
        "charred": "sinocraft:charred_dishes"	// 菜肴烧焦以后，可取出的成品。（可选，默认为烧焦的饭菜）
    }
}
```




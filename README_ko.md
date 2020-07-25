<img src="./logo.png" width="360"><br>

[English](./README.md)

[![Download](https://api.bintray.com/packages/minseunghyun/maven/slush/images/download.svg)](https://bintray.com/minseunghyun/maven/slush/_latestVersion)
![Android CI](https://github.com/MinSeungHyun/slush/workflows/Android%20CI/badge.svg)

**_더이상 반복적인 Adapter와 ViewHolder 코드를 작성하지 마세요._**  
슬러시가 RecyclerView를 쉽고 빠르게 사용할 수 있도록 도와줍니다.

_이 프로젝트의 목표는 엄청 복잡하지 않은 RecyclerView는 슬러시로 만들 수 있게 하는 것입니다._

## Setup

build.gradle(app)에 아래 dependency를 추가해주세요.

```groovy
dependencies {
    implementation 'in.seunghyun:slush:1.1.0'
}
```

## Basic Usage

`Slush`라는 하나의 클래스에서 모든 기능을 사용할 수 있습니다.

기본적으로 아래 코드를 작성해야 합니다.  
(여기서 `SimpleItem` 부분은 사용할 아이템 클래스로 바꿔주면 됩니다.)

```kotlin
Slush.SingleType<SimpleItem>()
    .setItemLayout(R.layout.simple_binding_item) // 아이템 레이아웃 설정
    .setLayoutManager(LinearLayoutManager(this)) // LayoutManager 설정 (이미 있다면 안써도 됨)
    .onBind { view, item ->
        view.itemName.text = item.name
    }
    .into(recyclerView)
```

`onBind`에서는 아이템 레이아웃의 뷰들을 초기화해줍니다. (기존 RecyclerView 어댑터에서 `onBindViewHolder`와 비슷합니다.)

### ItemListEditor

아이템 리스트를 수정할 수 있게 해주는 인터페이스입니다.

```kotlin
val result = Slush.SingleType<SimpleItem>()
    // 중간 코드 생략
    .into(recyclerView)
val itemListEditor = result.itemListEditor
```

사용 가능한 메소드는 [여기](https://github.com/MinSeungHyun/slush/wiki/ItemListEditor)에 나와 있습니다.

## DataBinding

슬러시는 데이터바인딩을 지원합니다.

데이터바인딩을 사용하려면 `onBind` 부분을 `onBindData` 로 바꿔야 합니다.

```kotlin
.onBindData<SimpleItemBinding> { binding, item ->
    binding.item = item
}
```

그리고 `SimpleItemBinding` 부분에 [생성된 바인딩 클래스](https://developer.android.com/topic/libraries/data-binding/generated-binding)를 넣어주면 됩니다.

## Options

### setItems

```kotlin
.setItems(items)
```

리스트에 들어갈 초기 아이템들을 설정해줍니다.

### onItemClick

```kotlin
.onItemClick { clickedView, position ->
    Log.d(TAG, "Clicked: $position")
}
```

아이템이 클릭 되었을 때 실행될 리스너를 설정해줍니다.

### setDiffCallback

```kotlin
.setDiffCallback(BasicDiffCallback())
```

[ItemListEditor](#ItemListEditor)의 `changeAll`을 호출할 때 사용되는 [DiffCallback](https://developer.android.com/reference/androidx/leanback/widget/DiffCallback)을 설정합니다.  
기본적으로 `changeAll`을 호출하면 리스트의 모든 데이터가 교체되지만 DiffCallback을 설정하면 수정된 부분만 바뀌기 때문에 애니메이션이 생깁니다.  
`SlushDiffCallback`을 상속하여 커스텀 DiffCallback을 사용할 수도 있습니다.

## Samples

[여기](https://github.com/MinSeungHyun/slush/tree/master/samples/src/main/java/com/example/slush)에서 샘플 코드를 볼 수 있습니다.

## License

```
MIT License

Copyright (c) 2020 SeungHyun

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

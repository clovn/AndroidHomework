package com.example.recycler_view.repository

import com.example.recycler_view.model.ButtonsHolderData
import com.example.recycler_view.model.MultiHoldersData
import com.example.recycler_view.model.TextImageHolderData
import com.example.recycler_view.utils.initCustomLayout
import com.example.recycler_view.utils.initGridLayout
import com.example.recycler_view.utils.initLinearLayout

object DataRepository {
    var items : MutableList<MultiHoldersData> = mutableListOf(
        ButtonsHolderData(
            0,
            "Список",
            "Сетка",
            "Custom",
            { recycler, ctx ->
                initLinearLayout(recycler, ctx)
            },
            { recycler, ctx ->
                initGridLayout(recycler, ctx)
            },
            { recycler, ctx ->
                initCustomLayout(recycler, ctx)
            }
        ),
        TextImageHolderData(
            1,
            "Зеленая долина с живописным видом",
            "Эта фотография представляет собой потрясающий вид на зеленую долину, окруженную горными хребтами.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1bFkzPy3tPsM4mez8oUi_Z4fokNBRTTgrY8WXnHUkHrh6Zry1I_5Yg-TkVduRkVNNATM&usqp=CAU"
        ),
        TextImageHolderData(
            2,
            "Горный пейзаж с туманом",
            "Туман окутал вершины гор, создавая мистическую атмосферу на фотографии.",
            "data:image/jpeg;base64,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"
        ),
        TextImageHolderData(
            3,
            "Горное озеро в утреннем свете",
            "Озеро отражает первые лучи утреннего солнца, освещая горные склоны.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBE7t_j03_0DGum6GcQUMBPm8HtIPR0Ie8Ig&s"
        ),
        TextImageHolderData(
            4,
            "Тропический пляж с пальмами",
            "Идиллический пляж с пальмами, омываемый бирюзовыми водами океана.",
            "https://img.freepik.com/premium-photo/tropical-beach-with-palm-trees-cloudy-sky_865967-34776.jpg"
        ),
        TextImageHolderData(
            5,
            "Мост через реку на закате",
            "Мост пересекает реку, а в небе разгорается яркий закат.",
            "https://ybis.ru/wp-content/uploads/2023/09/most-na-zakate-1.webp"
        ),
        TextImageHolderData(
            6,
            "Осенний лес с золотыми листьями",
            "Осенние листья создают золотое покрывало на лесной дорожке.",
            "https://ybis.ru/wp-content/uploads/2023/09/zheltye-listia-v-lesu-1.webp"
        ),
        TextImageHolderData(
            7,
            "Горный хребет в облаках",
            "Горы скрыты в облаках, создавая захватывающий вид.",
            "https://99px.ru/sstorage/53/2015/07/tmb_136744_9345.jpg"
        ),
        TextImageHolderData(
            8,
            "Закат на побережье океана",
            "Морской берег освещен последними лучами заката, создавая романтическую атмосферу.",
            "https://u-stena.ru/upload/iblock/933/9334937172450c7fc1b0209ae8bebc17.jpg"
        ),
        TextImageHolderData(
            9,
            "Гроза над озером",
            "Гроза приближается к озеру, создавая драматичный и мощный пейзаж.",
            "https://cs14.pikabu.ru/post_img/2023/11/03/5/1698992460128912820.jpg"
        ),
        TextImageHolderData(
            10,
            "Пеший туризм в горах",
            "Туристы наслаждаются походом по горному маршруту среди величественных скал.",
            "https://объясняем.рф/upload/iblock/f39/qw281ney5fvo6fxt05qgthey1ql9gu5i/RIA_6051867.HR.jpg"
        ),
        TextImageHolderData(
            11,
            "Северное сияние в ночном небе",
            "Красочные огни северного сияния танцуют в темном ночном небе.",
            "https://s0.rbk.ru/v6_top_pics/media/img/7/84/346823210501847.webp"
        ),
        TextImageHolderData(
            12,
            "Лаванда на фоне гор",
            "Поле лаванды с фоном гор создает умиротворяющий и красивый пейзаж.",
            "https://img.freepik.com/premium-photo/field-lavender-mountains-with-mountain-background_911849-42405.jpg"
        ),
        TextImageHolderData(
            13,
            "Голубое озеро в горах",
            "Голубое озеро отражает скалистые горы и зелень лесов вокруг.",
            "https://www.kartinki24.ru/uploads/gallery/main/194/kartinki24_ru_lake_84.jpg"
        ),
        TextImageHolderData(
            14,
            "Шумный водопад среди леса",
            "Мощные потоки воды падают с водопада, окруженного густым лесом.",
            "https://ybis.ru/wp-content/uploads/2023/10/amazonskie-dzhungli-tropicheskii-vodopad-4.webp"
        ),
        TextImageHolderData(
            15,
            "Лес в тумане",
            "Туман скрывает лес, создавая загадочный и мистический пейзаж.",
            "https://oboitd.ru/images/goods/big/20230109080507_Les_v_tumane_8453-M.jpg"
        ),
        TextImageHolderData(
            16,
            "Природный ландшафт с холмами",
            "Легкие холмы и зелень создают живописный и спокойный пейзаж.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWoTP1nZsMDl65fa3JlP09h9u1TRp6TeXBGA&s"
        ),
        TextImageHolderData(
            17,
            "Поля с пшеницей под небом",
            "Золотые поля пшеницы сливаются с голубым небом, создавая гармонию природы.",
            "https://pw.artfile.me/wallpaper/21-11-2017/650x379/cvety-tyulpany-pole-priroda-oblaka-golub-1267443.jpg"
        ),
        TextImageHolderData(
            18,
            "Горная долина с рекой",
            "Тихая река петляет между зелеными горами и залитыми солнцем склонами.",
            "https://masterpiecer-images.s3.yandex.net/1b07445050eb11ee91a002c370ea8a2f:upscaled"
        ),
        TextImageHolderData(
            19,
            "Закат над морем",
            "Теплый закат окрашивает волны и горизонт в золотисто-розовые оттенки.",
            "https://i.pinimg.com/originals/ff/6c/c0/ff6cc082d4e89775adf9b84081c1eba3.jpg"
        ),
        TextImageHolderData(
            20,
            "Зимний лес",
            "Снежные деревья создают волшебную атмосферу зимнего леса.",
            "https://images.wallpapershq.com/wallpapers/2838/wallpaper_2838_3840x2160.jpg"
        ),
        TextImageHolderData(
            21,
            "Озеро среди гор",
            "Чистое голубое озеро отражает величественные вершины гор.",
            "https://avatars.mds.yandex.net/get-shedevrum/11509417/58f3d9e8c87b11ee9e83baaee90618f0/orig"
        ),
        TextImageHolderData(
            22,
            "Поле с подсолнухами",
            "Солнечные подсолнухи тянутся к ясному небу, создавая яркий пейзаж.",
            "https://www.newseventsturin.net/wp-content/uploads/2020/06/podsolnuh-piemont.webp"
        ),
        TextImageHolderData(
            23,
            "Весенний лес",
            "Цветущие деревья и свежая зелень знаменуют приход весны.",
            "https://storage.yandexcloud.net/storage.yasno.media/nat-geo/images/2019/5/16/d69205571b114cc7a55987611d86cc5f.max-2500x1500.jpg"
        ),
        TextImageHolderData(
            24,
            "Каньон с рекой",
            "Величественный каньон обрамляет извивающуюся реку на его дне.",
            "https://trip-guru.ru/images/easyblog_articles/120/b2ap3_amp_rip-guru.ru0015.jpg"
        ),
        TextImageHolderData(
            25,
            "Осенний парк",
            "Золотая осень расцвечивает деревья и опавшие листья яркими красками.",
            "https://photocentra.ru/images/main101/1010199_main.jpg"
        )

    )
}
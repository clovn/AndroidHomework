package com.example.recycler_view.repository

import com.example.recycler_view.model.TextImageHolderData

object RandomRepository {
    private var id = 26

    private val titles = listOf(
        "Вечное пламя", "Тихий лес", "Золотой горизонт", "Танцующие тени",
        "Алый закат", "Шепчущие волны", "Изумрудный сон", "Замерзшие глубины",
        "Сияющий цветок", "Скрытая тропа", "Симфония сумерек", "Разбитый свет",
        "Небесное путешествие", "Эфирное свечение", "Мистические равнины", "Бархатная ночь",
        "Светящийся лабиринт", "Спокойные отражения", "Яркий мираж", "Бесконечные небеса"
    )

    private val descriptions = listOf(
        "Вдохновляющее путешествие в неизвестность.",
        "Тишина леса, запечатленная в ярких красках.",
        "Сюрреалистическое изображение встречи земли и неба.",
        "Игривая игра света и тени.",
        "Захватывающий вид огненного горизонта.",
        "Волны, мягко шепчущие тайны океана.",
        "Сказочная страна, наполненная оттенками зелени.",
        "Ледяное спокойствие неисследованных глубин океана.",
        "Яркий и живой взрыв красок.",
        "Загадочная тропа, окруженная тайной.",
        "Успокаивающая смесь цветов на закате.",
        "Фрагментированная игра света.",
        "Приключенческое путешествие за пределы звезд.",
        "Мягкое и волшебное свечение ночного неба.",
        "Изображение нетронутой мистической земли.",
        "Спокойные объятия темного и умиротворяющего.",
        "Извилистый лабиринт света и тени.",
        "Спокойное отражение красоты природы.",
        "Яркая иллюзия, воплощенная в жизнь.",
        "Бесконечные просторы неба и мечтаний."
    )

    private val images = listOf(
        "https://img.freepik.com/free-photo/dreamlike-surrealistic-landscape-purplish-tones_23-2150505352.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/3d-render-tranquil-island-sunset_1048-7534.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/beautiful-plants-natural-environment_23-2151357867.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/nature-landscape-with-dreamy-aesthetic-color-year-tones_23-2151393932.jpg?semt=ais_hybrid",
        "https://img.freepik.com/premium-photo/podium-with-background-presenting-mock-up-products_7023-3259.jpg?semt=ais_hybrid",
        "https://img.freepik.com/premium-photo/circle-wooden-podium-with-blur-bokeh-background-lake-forest-3d-render-summer-background_361738-231.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/digital-art-isolated-house_23-2151041248.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/ai-generated-medieval-fantasy-cities_23-2150694069.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/nature-landscape-with-black-sand-beach_23-2151380345.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/nature-serene-landscape-reflecting-tranquil-scene-through-water-generative-ai_188544-12645.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/vibrant-underwater-landscape-with-colorful-fish-blooming-flowers-generated-by-artificial-intelligence_188544-241351.jpg?semt=ais_hybrid",
        "https://img.freepik.com/premium-photo/nature-scenery-river-with-podium-product-display-scene_485324-1204.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/floating-islands-background_1048-1925.jpg?semt=ais_hybrid",
        "https://img.freepik.com/premium-photo/island-floating_534373-11976.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/view-rock-formations-with-nature-landscape_23-2151723160.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/vertical-aerial-shot-eltz-castle-surrounded-with-clouds-trees-germany_181624-2231.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/beautiful-sea-landscape-with-water-nature_23-2151120271.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/beautiful-interior-design-with-natural-view_23-2150697266.jpg?semt=ais_hybrid",
        "https://img.freepik.com/free-photo/futuristic-representation-water-home-architecture_23-2151048181.jpg?semt=ais_hybrid"
    )

    fun generateRandomItem() : TextImageHolderData{
        return TextImageHolderData(
            id = id++,
            text = titles.random(),
            desc = descriptions.random(),
            imageUrl = images.random()
        )
    }
}
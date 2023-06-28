package com.github.hosseinzafari.touristo.core.data.data_model

import android.net.Uri
import com.github.hosseinzafari.touristo.core.data.local.fake_data.fakeUsers
import com.github.hosseinzafari.touristo.core.data.local.fake_data.toUser
import com.queezo.app.assets.card_1_1
import com.queezo.app.assets.card_1_2
import com.queezo.app.assets.card_2_1
import com.queezo.app.assets.card_2_2

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:28 AM
 * @project Touristo
 */

data class LocationModel(
    var id: Int,
    var desc: String,
    var resID: Int,
    var name: String,
    var location: ProvinceModel,
    var likeCount: Int,
    var categoryModel: CategoryModel,
    var comments: MutableList<CommentModel>,
    var imageUri: Uri? = null ,
)


val LocationData: MutableList<LocationModel> by lazy {
    mutableListOf(
        LocationModel(
            1,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد",
            card_1_1,
            "جنگل ماسوله",
            provinceData.filter { it.name == "مازندران" }.first(),
            54,
            categories.filter { it.title == "جنگل" }.first(),
            mutableListOf(
                CommentModel(1, fakeUsers.toUser().get(0), "بسیار عالی بود متشکرم"),
                CommentModel(
                    2,
                    fakeUsers.toUser().get(0),
                    "لفا بیشتر از این پست ها باز هم بگذارید خیلی باحال هستش"
                ),
                CommentModel(
                    3,
                    fakeUsers.toUser().get(1),
                    "حس خیلی خوبی به ادم دست میده وقتی نگاهش میکنی"
                ),
                CommentModel(
                    4,
                    fakeUsers.toUser().get(2),
                    "به نظر میاد جای قشنگی باشه حتما من اگه رفتم واستون عکسشو می فرستم بچه ها"
                ),
                CommentModel(
                    5,
                    fakeUsers.toUser().get(3),
                    "اگه خدا کمک کنه با خانواده به زودی میریم اونجا"
                ),
            )
        ),
        LocationModel(
            2,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد"
            ,card_1_2, "دشت مغان", provinceData.filter { it.name == "مازندران" }.first(), 58,
            categories.filter { it.title == "جنگل" }.first(), mutableListOf(
                CommentModel(
                    6,
                    fakeUsers.toUser().get(4),
                    "بسیار عالی بود حتما دیدن خواهم کرد"
                ),
                CommentModel(
                    7,
                    fakeUsers.toUser().get(2),
                    "نشون از طبیعت گردی شما رو میرسونه بسیار عالی!"
                ),
                CommentModel(
                    8,
                    fakeUsers.toUser().get(5),
                    "ما هفته پیش رفتیم کمی اونجا متاسفانه آشغال انداخته بودن با دوستان اونجا رو هم تمیز کردیم عالی شد."
                ),
                CommentModel(
                    9,
                    fakeUsers.toUser().get(3),
                    "امیدوارم برای آیندگان هم بمونه همچین طبیعتی"
                ),
            )
        ),
        LocationModel(
            3,"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد"
            , card_2_1, "بندر چابهار", provinceData.filter { it.name == "فارس" }.first(), 80,
            categories.filter { it.title == "دریا" }.first(), mutableListOf(
                CommentModel(10, fakeUsers.toUser().get(5), "مردمان مهان نواز جنوب عالی هستن"),
                CommentModel(11, fakeUsers.toUser().get(1), "بندر داره مدرن تر میشه خدارشکر"),
                CommentModel(
                    12,
                    fakeUsers.toUser().get(3),
                    "امیدوارم به زودی برم اونجا دوباره"
                ),
            )
        ),
        LocationModel(
            4, "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد" ,card_2_2, "دریاچه چیتگر", provinceData.filter { it.name == "البرز" }.first(), 20,
            categories.filter { it.title == "دریا" }.first(), mutableListOf(
                CommentModel(
                    13,
                    fakeUsers.toUser().get(0),
                    "پارسال با بردارم رفتیم اونجا بد جایی نبود"
                ),
                CommentModel(
                    14,
                    fakeUsers.toUser().get(2),
                    "تو ایام کرونا بسته بودن متاسفانه اما الان که باز شده هر ماه میریم اونجا"
                ),
                CommentModel(
                    15,
                    fakeUsers.toUser().get(4),
                    "پارسال که رفتیم بارون میومد خیلی جو خوبی به آدم دست میداد امیدوارم این هفته با همسرم دوباره بریم"
                ),
            )
        ),
    )
}
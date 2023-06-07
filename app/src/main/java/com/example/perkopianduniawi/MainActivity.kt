package com.example.perkopianduniawi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perkopianduniawi.adapters.AdapterView
import com.example.perkopianduniawi.databinding.ActivityMainBinding
import com.example.perkopianduniawi.models.CafeData

class MainActivity : AppCompatActivity() {
    private lateinit var layoutBind: ActivityMainBinding
    private lateinit var mainAdapter: AdapterView
    private lateinit var cafeData: List<CafeData>
    private var filteredCafeData: List<CafeData> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layoutBind.root)

        initSearchBox()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        listOf(
            // Region Kota Madiun
            CafeData("Ikio Coffee", "Lorem ipsum dolor sit amet", 4.6, "Jl. Eka Karya No.1, Mojorejo, Kec. Taman, Kota Madiun, Jawa Timur 63139", "08.00", "23.00", "https://goo.gl/maps/mfZNEEksou2Fuhhs8"),
            CafeData("Gemar Coffee", "Lorem ipsum dolor sit amet", 4.7, "Jl. S. Parman No.24a, Nongkobener, Oro-oro Ombo, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63119", "08.00", "23.00", "https://goo.gl/maps/XckN5QmQR4S6cAoG7"),
            CafeData("Belle Coffee And Chill", "Lorem ipsum dolor sit amet", 4.7, "Jl. Kemiri No.22A, Kejuron, Kec. Taman, Kota Madiun, Jawa Timur 63132", "09.00", "23.30", "https://goo.gl/maps/1RBnBr31ZKxKGZ3D8"),
            CafeData("Kopi Soe", "Lorem ipsum dolor sit amet", 4.5, "Jl. H.A. Salim No.58 B, Nambangan Lor, Kec. Manguharjo, Kota Madiun, Jawa Timur 63129", "09.00", "22.00", "https://goo.gl/maps/UDEBNLo6XgTdn5J87"),
            CafeData("Balen Coffee", "Lorem ipsum dolor sit amet", 4.8, "Jl. Kemiri No.19, Taman, Kec. Taman, Kota Madiun, Jawa Timur 63118", "09.00", "23.00", "https://goo.gl/maps/APkMBSbFNheebw9G6"),
            CafeData("Kopi Kakak", "Lorem ipsum dolor sit amet", 4.4, "Jl. Kolonel Marhadi No.27, Nambangan Lor, Kec. Manguharjo, Kota Madiun, Jawa Timur 63129", "11.00", "22.00", "https://goo.gl/maps/7RGykuPQm9ynAPHx7"),
            CafeData("Cafe Arum Dalu", "Lorem ipsum dolor sit amet", 4.6, "Jl. Yos Sudarso No.71 (Kompleks Manufaktur Kereta Api INKA), Madiun Lor, Kec. Manguharjo, Kota Madiun, Jawa Timur 63122", "10.00", "21.00", "https://goo.gl/maps/gonfLAKJAD9BsVgF8"),
            CafeData("Bento Kopi Madiun", "Lorem ipsum dolor sit amet", 4.3, "Perumahan bumi mas III Jl. Taman Praja No.7, Mojorejo, Kec. Taman, Kota Madiun, Jawa Timur 63139", "09.00", "02.00", "https://goo.gl/maps/pyCJAD9JnbyhK7Es7"),
            CafeData("Raya Cafe and Resto Madiun", "Lorem ipsum dolor sit amet", 4.4, "Jl. Wuni No.7, Kejuron, Kec. Taman, Kota Madiun, Jawa Timur 63132", "09.00", "22.00", "https://goo.gl/maps/CuhgkRcXmGeGBEU87"),
            CafeData("Dot Max Coffee and Eatery", "Lorem ipsum dolor sit amet", 4.7, "Jl. Kapten Saputra No.87, Taman, Kec. Taman, Kota Madiun, Jawa Timur 63131", "11.00", "23.00", "https://goo.gl/maps/5JvKe8RAG3WR698VA"),
            CafeData("Kohan Cafe & Eatery", "Lorem ipsum dolor sit amet", 4.5, "Jl. Sulawesi No.41, Kartoharjo, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63117", "11.00", "23.00", "https://goo.gl/maps/PHwJGTsJtqvSJ7U19", false),
            CafeData("Heavun - Eat, Drink, Fun", "Lorem ipsum dolor sit amet", 4.6, "Jl. Sulawesi No.13, Kartoharjo, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63116", "12.00", "23.00", "https://goo.gl/maps/Z9XSWL698wWweifX6"),
            CafeData("Forest Cafe (Angkringan Wedding Outdoor)", "Lorem ipsum dolor sit amet", 4.5, "Jl. Rimbakaya, Kartoharjo, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63117", "09.00", "21.00", "https://goo.gl/maps/LuhUQCQkoFQa6YQ36"),
            CafeData("Arten Cafe", "Lorem ipsum dolor sit amet", 4.5, "Jl. Abdulrahman Saleh No.10, Kejuron, Kec. Taman, Kota Madiun, Jawa Timur 63132", "11.00", "23.00", "https://goo.gl/maps/dJNpvRwLxQ5VdtYi9"),
            CafeData("WISDOM Coffee & Roastery", "Lorem ipsum dolor sit amet", 4.6, "Jl. Kapten Saputra No.13, Kejuron, Kec. Taman, Kota Madiun, Jawa Timur 63132", "09.00", "23.00", "https://goo.gl/maps/oAT1uEymVPzivrDH7"),
            CafeData("Bonaboni Gelato, Coffee and Brownies", "Lorem ipsum dolor sit amet", 4.7, "Jl. Eka Jaya, Klegen, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63117", "10.00", "23.00", "https://goo.gl/maps/xUGkfobvEJZuodvY9"),
            CafeData("Paratamu Coffee", "Lorem ipsum dolor sit amet", 4.7, "Jl. Karta Wijaya No.17, Klegen, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63117", "08.00", "23.00", "https://goo.gl/maps/421KGHGZmzLq4aGcA"),
            CafeData("Jiyo Coffee&Space", "Lorem ipsum dolor sit amet", 4.8, "Jl. Slamet Riyadi No.59b, Kanigoro, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63117", "09.00", "23.00", "https://goo.gl/maps/GbfwJ2pfrgPUCBeC8"),
            CafeData("ANGKRINGAN RUTE57", "Lorem ipsum dolor sit amet", 4.5, "Kanigoro, Kec. Kartoharjo, Kota Madiun, Jawa Timur 63118", "10.00", "23.00", "https://goo.gl/maps/csg3sMZTcSkYcNm69"),

            // Kota Surabaya
            CafeData("Kopi Merr", "Lorem ipsum dolor sit amet", 4.5, "Jl. Dr. Ir. H. Soekarno, Gn. Anyar, Kec. Gn. Anyar, Surabaya, Jawa Timur 60294", "08.00", "23.00", "https://goo.gl/maps/eb63EspRZgEP71iE8", region = "Kota Surabaya"),
            CafeData("Titik Tengah Kopi Merr 2.0", "Lorem ipsum dolor sit amet", 5.0, "Jl. Dr. Ir. H. Soekarno No.487, Penjaringan Sari, Kec. Rungkut, Kota SBY, Jawa Timur 60293", "08.00", "23.00", "https://goo.gl/maps/eb63EspRZgEP71iE8", false, "Kota Surabaya"),
            CafeData("MOOGS Coffee", "Lorem ipsum dolor sit amet", 4.6, "Rungkut harapan utara B 15, Surabaya, East Java", "10.00", "00.00", "https://goo.gl/maps/S9astZFnMvCc6PEF7", region = "Kota Surabaya"),

            // Region Ngawi
            CafeData("Portafighter Roastery (Cafe Ngawi / Kedai Kopi Ngawi)", "Lorem ipsum dolor sit amet", 4.5, "Jl. Brawijaya No.13, Kerek, Margomulyo, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63271", "09.00", "22.00", "https://goo.gl/maps/r3gitfLy6sDYh9S77", region = "Ngawi"),
            CafeData("Portafighter #2 Roastery (Cafe Ngawi / Kedai Kopi Ngawi)", "Lorem ipsum dolor sit amet", 4.7, "Jl. Raya Paron No.15-27, Kenaiban, Paron, Kec. Paron, Kabupaten Ngawi, Jawa Timur 63253", "10.00", "22.00", "https://goo.gl/maps/J6AuvvymwYjhFoJe9", region ="Ngawi"),
            CafeData("Floe Coffee & Space", "Lorem ipsum dolor sit amet", 4.8, "Jl. Raya Ngawi - Caruban No.KM 2, Jambe, Karangasri, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63218", "11.00", "23.00", "https://goo.gl/maps/p3ESayZzvBNdFbt6A", region = "Ngawi"),
            CafeData("THX Coffee", "Lorem ipsum dolor sit amet", 4.7, "Jl. Ahmad Yani No.5, Beran I, Beran, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63216", "13.00", "23.00", "https://goo.gl/maps/KYNv1xCg83tkcKDc9", region = "Ngawi"),
            CafeData("Glory Coffeenery", "Lorem ipsum dolor sit amet", 4.7, "Jl. Trunojoyo, RT.04/RW.03, Kerek, Margomulyo, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63217", "10.00", "23.00", "https://goo.gl/maps/fXEj9WC4ocipkfKq9", region = "Ngawi"),
            CafeData("Adess Pool & Cafe", "Lorem ipsum dolor sit amet", 3.9, "Jl. Teuku Umar, Kerek, Margomulyo, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63211", "10.00", "22.00", "https://goo.gl/maps/CqkSU5sDKMhUvWJK6", region = "Ngawi"),
            CafeData("The Real Coffee and Eatery", "Lorem ipsum dolor sit amet", 4.6, "Jl. Supriyadi, Dungus, Karangasri, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63218", "10.00", "23.00", "https://goo.gl/maps/N6xivhnhXebZA5Uc7", region = "Ngawi"),
            CafeData("La Densa", "Lorem ipsum dolor sit amet", 4.2, "Jl. Raden Saleh No.6, Cabean Kidul, Ketanggi, Kec. Ngawi, Kabupaten Ngawi, Jawa Timur 63211", "10.00", "22.00", "https://goo.gl/maps/w1JE8BmW1qezPgpR9", region = "Ngawi"),

            // Klaten
            CafeData("57 Terus Coffee Klaten", "Lorem ipsum dolor sit amet",  5.0, "Jl. Mayor Kusmanto, Sungkur, Semangkak, Kec. Klaten Utara, Kabupaten Klaten, Jawa Tengah", "10.00", "22.00", "https://goo.gl/maps/mHUKfvMUgjnfkNDX8", region = "Klaten"),
            CafeData("Manah Coffee", "Lorem ipsum dolor sit amet",  4.9, "Jl. Merbabu, Gayamprit, Kec. Klaten Sel., Kabupaten Klaten, Jawa Tengah 57423", "08.00", "23.00", "https://goo.gl/maps/EbqdPsqUkawq6XGaA", region = "Klaten"),
            CafeData("Sebelas Coffee Crafter Klaten", "Lorem ipsum dolor sit amet",  4.6, "Jl. Merbabu No.9A, Gayamprit, Kec. Klaten Sel., Kabupaten Klaten, Jawa Tengah 57423", "09.00", "23.00", "https://goo.gl/maps/ucdqjbQZmZXyutov6", region = "Klaten"),
            CafeData("Cold ‘N Brew Coffee Shop Klaten", "Lorem ipsum dolor sit amet",  4.7, "Jl. Pemuda No.292, Mlinjon, Tonggalan, Kec. Klaten Tengah, Kabupaten Klaten, Jawa Tengah 57412", "07.00", "23.00", "https://goo.gl/maps/G4CrSbdSi34pj3hZ8", region = "Klaten"),
        ).also { cafeData = it }

        val recyclerView = layoutBind.recyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        mainAdapter = AdapterView(cafeData)
        recyclerView.adapter = mainAdapter
    }

    private fun initSearchBox() {
        layoutBind.textSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                filteredCafeData = if (query.isEmpty()) {
                    cafeData // Show all cafes if search query is empty
                } else {
                    cafeData.filter { cafe ->
                        cafe.name.contains(query, ignoreCase = true)
                    }
                }
                mainAdapter.updateData(filteredCafeData)
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }
}

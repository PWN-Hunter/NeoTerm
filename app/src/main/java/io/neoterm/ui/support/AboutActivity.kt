package io.neoterm.ui.support

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense30
import de.psdev.licensesdialog.licenses.MITLicense
import de.psdev.licensesdialog.model.Notice
import de.psdev.licensesdialog.model.Notices
import io.neoterm.App
import io.neoterm.R
import io.neoterm.ui.setup.SetupActivity


/**
 * @author kiva
 */
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_about)
        setSupportActionBar(findViewById(R.id.about_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val version = packageManager.getPackageInfo(packageName, 0).versionName
            (findViewById<TextView>(R.id.app_version)).text = version
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        findViewById<View>(R.id.about_developers_view).setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle(R.string.about_developers_label)
                    .setMessage(R.string.about_developers)
                    .setPositiveButton(android.R.string.yes, null)
                    .show()
        }

        findViewById<View>(R.id.about_licenses_view).setOnClickListener {
            val notices = Notices()
            notices.addNotice(Notice("ADBToolkitInstaller", "https://github.com/Crixec/ADBToolKitsInstaller", "Copyright (c) 2017 Crixec", GnuGeneralPublicLicense30()))
            notices.addNotice(Notice("Android-Terminal-Emulator", "https://github.com/jackpal/Android-Terminal-Emulator", "Copyright (c) 2011-2016 Steven Luo", ApacheSoftwareLicense20()))
            notices.addNotice(Notice("ChromeLikeTabSwitcher", "https://github.com/michael-rapp/ChromeLikeTabSwitcher", "Copyright (c) 2016-2017 Michael Rapp", ApacheSoftwareLicense20()))
            notices.addNotice(Notice("Color-O-Matic", "https://github.com/GrenderG/Color-O-Matic", "Copyright 2016-2017 GrenderG", GnuGeneralPublicLicense30()))
            notices.addNotice(Notice("EventBus", "http://greenrobot.org", "Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)", ApacheSoftwareLicense20()))
            notices.addNotice(Notice("ModularAdapter", "https://wrdlbrnft.github.io/ModularAdapter", "Copyright (c) 2017 Wrdlbrnft", MITLicense()))
            notices.addNotice(Notice("RecyclerTabLayout", "https://github.com/nshmura/RecyclerTabLayout", "Copyright (C) 2017 nshmura", ApacheSoftwareLicense20()))
            notices.addNotice(Notice("RecyclerView-FastScroll", "Copyright (c) 2016, Tim Malseed", "Copyright (c) 2016, Tim Malseed", ApacheSoftwareLicense20()))
            notices.addNotice(Notice("SortedListAdapter", "https://wrdlbrnft.github.io/SortedListAdapter/", "Copyright (c) 2017 Wrdlbrnft", MITLicense()))
            notices.addNotice(Notice("Termux", "https://termux.com", "Copyright 2016-2017 Fredrik Fornwall", GnuGeneralPublicLicense30()))
            LicensesDialog.Builder(this)
                    .setNotices(notices)
                    .setIncludeOwnLicense(true)
                    .build()
                    .show()
        }

        findViewById<View>(R.id.about_version_view).setOnClickListener {
            App.get().easterEgg(this, "Emmmmmm...")
        }

        findViewById<View>(R.id.about_source_code_view).setOnClickListener {
            openUrl("https://github.com/NeoTerm/NeoTerm")
        }

        findViewById<View>(R.id.about_donate_view).setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle(R.string.support_donate_label)
                    .setMessage(R.string.support_donate_dialog_text)
                    .setPositiveButton(R.string.support_donate_alipay, { _, _ ->
                        Donation.donateByAlipay(this, "FKX025062MBLAG6E90RYBC")
                    })
                    .setNeutralButton(android.R.string.no, null)
                    .show()
        }

        findViewById<View>(R.id.about_show_help_view).setOnClickListener {
            App.get().openHelpLink();
        }

        findViewById<View>(R.id.about_reset_app_view).setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage(R.string.reset_app_warning)
                    .setPositiveButton(R.string.yes, { _, _ ->
                        resetApp()
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show()
        }
    }

    private fun resetApp() {
        startActivity(Intent(this, SetupActivity::class.java))
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
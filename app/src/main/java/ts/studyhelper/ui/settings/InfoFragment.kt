package ts.studyhelper.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ts.studyhelper.BuildConfig
import ts.studyhelper.R

class InfoFragment : Fragment(R.layout.fragment_info) {
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gateVersion : TextView = view.findViewById(R.id.textVersion)
        val  gateType : TextView = view.findViewById(R.id.typeText)

        gateVersion.text = "version: ${getVersion()}"
        gateType.text = "type: ${getType()}"
    }

    private fun getVersion(): String{
        return BuildConfig.VERSION_NAME
    }

    private fun getType() : String{
        val isrelease : Boolean = false
        val textTypes : String
        if (isrelease){
            textTypes = "RELEASE"
        }
        else{
            textTypes = "BETA"
        }
        return textTypes
    }
}
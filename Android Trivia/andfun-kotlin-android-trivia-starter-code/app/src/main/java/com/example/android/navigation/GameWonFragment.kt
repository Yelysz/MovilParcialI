package com.example.android.navigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.navigation.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    private val args: GameWonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGameWonBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        Toast.makeText(
            requireContext(),
            "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
            Toast.LENGTH_LONG
        ).show()

        setHasOptionsMenu(true) // si prefieres MenuProvider, te dejo nota abajo
        return binding.root
    }

    private fun getShareIntent(): Intent =
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                getString(
                    R.string.share_success_text,
                    args.numCorrect,
                    args.numQuestions
                )
            )
        }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu, menu)
        // Ocultar si no hay app que maneje el intent
        if (getShareIntent().resolveActivity(requireActivity().packageManager) == null) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.share -> { shareSuccess(); true }
            else -> super.onOptionsItemSelected(item)
        }
}

package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MessagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages, container, false);

        view.findViewById(R.id.message1).setOnClickListener(v -> navigateToConversation("1"));
        view.findViewById(R.id.message2).setOnClickListener(v -> navigateToConversation("2"));
        view.findViewById(R.id.message3).setOnClickListener(v -> navigateToConversation("3"));
        view.findViewById(R.id.message4).setOnClickListener(v -> navigateToMessagesAlice());

        return view;
    }

    private void navigateToConversation(String conversationId) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).navigateToFragment(ConversationFragment.newInstance(conversationId), "Conversazione " + conversationId, true);
        }
    }

    private void navigateToMessagesAlice() {
        Fragment messagesAliceFragment = new MessagesAliceFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, messagesAliceFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
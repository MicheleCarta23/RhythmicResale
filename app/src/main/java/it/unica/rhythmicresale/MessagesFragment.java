package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class MessagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages, container, false);

        view.findViewById(R.id.message1).setOnClickListener(v -> navigateToConversation("1"));
        view.findViewById(R.id.message2).setOnClickListener(v -> navigateToConversation("2"));
        view.findViewById(R.id.message3).setOnClickListener(v -> navigateToConversation("3"));

        return view;
    }

    private void navigateToConversation(String conversationId) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).navigateToFragment(ConversationFragment.newInstance(conversationId), "Conversazione " + conversationId, true);
        }
    }
}

package it.unica.rhythmicresale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class ConversationFragment extends Fragment {

    private static final String ARG_CONVERSATION_ID = "conversation_id";
    private String conversationId;

    public static ConversationFragment newInstance(String conversationId) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONVERSATION_ID, conversationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            conversationId = getArguments().getString(ARG_CONVERSATION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        LinearLayout conversationLayout = view.findViewById(R.id.conversation_layout);
        EditText inputMessage = view.findViewById(R.id.input_message);
        AppCompatImageButton sendButton = view.findViewById(R.id.send_button);  // Cambia da Button a AppCompatImageButton se necessario
        ScrollView scrollView = view.findViewById(R.id.scroll_view);

        Map<String, String[]> conversations = getConversations();

        if (conversations.containsKey(conversationId)) {
            String[] messages = conversations.get(conversationId);
            assert messages != null;
            for (String message : messages) {
                addMessage(conversationLayout, message);
            }
        }

        sendButton.setOnClickListener(v -> {
            String messageText = inputMessage.getText().toString().trim();
            if (!messageText.isEmpty()) {
                inputMessage.setText("");
                scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
            } else {
                Toast.makeText(getContext(), "Non puoi inviare un messaggio vuoto", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private Map<String, String[]> getConversations() {
        Map<String, String[]> conversations = new HashMap<>();
        conversations.put("1", new String[]{
                "buyer::Ciao ti contatto per l'annuncio del Sax soprano, è ancora disponibile?::10:00::sax",
                "auto::Messaggio automatico: l'utente è in vacanza e non può rispondere.::10:01"
        });
        conversations.put("2", new String[]{
                "buyer::Ciao ti contatto per l'annuncio del Bassotuba, è ancora disponibile?::11:00::bassotuba",
                "seller::Sì certo è disponibile!::11:01",
                "buyer::Ho visto che costa 600€::11:05",
                "buyer::Saresti disposto a scendere a 400€::11:06",
                "seller::Mi dispiace ma non posso scendere ulteriormente di prezzo.::11:10",
        });
        conversations.put("3", new String[]{
                "buyer::Ciao ti contatto per l'annuncio del Telecaster American Pro, è disponibile?::12:00::tele",
                "seller::Ciao si è disponibile.::12:01",
                "buyer::Dato che è un ottimo prezzo e non voglio lasciarmelo scappare lo prendo allora!::12:05",
                "buyer::Dove possiamo incontrarci?::12:06",
                "seller::Questo è il mio indirizzo::12:10",
                "seller::Via Bell'Aria, 4 - Belluno::12:11",
                "buyer::Va bene grazie::12:15",
                "auto::Hai effettuato l'acquisto!::12:20",
                "seller::Grazie a te per l'acquisto!::12:25"
        });
        return conversations;
    }

    private void addMessage(LinearLayout layout, String message) {
        String[] parts = message.split("::", 4);
        String sender = parts[0];
        String content = parts[1];
        String time = parts[2];
        String imageName = parts.length > 3 ? parts[3] : null;

        LinearLayout messageLayout;
        if (imageName != null) {
            messageLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.message_image, layout, false);

            ImageView imageView = messageLayout.findViewById(R.id.message_image);
            int imageResId = getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
            imageView.setImageResource(imageResId);

            TextView textView = messageLayout.findViewById(R.id.message_text);
            textView.setText(content);

            TextView timeView = messageLayout.findViewById(R.id.message_time);
            timeView.setText(time);

        } else {
            messageLayout = new LinearLayout(getContext());
            messageLayout.setOrientation(LinearLayout.VERTICAL);
            messageLayout.setPadding(16, 8, 16, 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 24, 0, 24);
            messageLayout.setLayoutParams(params);

            TextView textView = new TextView(getContext());
            textView.setText(content);
            textView.setTextSize(16);

            TextView timeView = new TextView(getContext());
            timeView.setText(time);
            timeView.setTextSize(12);
            timeView.setTextColor(getResources().getColor(android.R.color.darker_gray));

            messageLayout.addView(textView);
            messageLayout.addView(timeView);

            switch (sender) {
                case "seller":
                    messageLayout.setBackgroundResource(R.drawable.seller_message_bg);
                    params.gravity = RelativeLayout.ALIGN_PARENT_START;
                    break;
                case "buyer":
                    messageLayout.setBackgroundResource(R.drawable.buyer_message_bg);
                    params.gravity = RelativeLayout.ALIGN_PARENT_END;
                    break;
                case "auto":
                    messageLayout.setBackgroundResource(R.drawable.auto_message_bg);
                    params.gravity = RelativeLayout.CENTER_HORIZONTAL;
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    break;
            }
        }

        layout.addView(messageLayout);
    }
}

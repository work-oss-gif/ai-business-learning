package com.aibusiness.learning;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Each lesson: title, explanation, quiz question, three options, index of the right option, why.
    private static final String[][] LESSONS = {
        {
            "1. What AI really is",
            "Modern business AI mostly means 'generative AI': software trained on huge amounts of text, images or data "
            + "that can write, summarise, answer questions and spot patterns.\n\n"
            + "Think of it as a very fast, very well-read assistant. It is great at first drafts and sorting through "
            + "information. It is NOT a person with judgement, and it does not 'know' your business unless you tell it.",
            "Which description fits business AI best?",
            "A fast assistant that drafts and summarises|A replacement for all decisions|A search engine that is always right",
            "0",
            "AI works best as a fast assistant. You still supply the goals, the facts and the final judgement."
        },
        {
            "2. Where AI helps most",
            "Look for work that is repetitive, text-heavy or pattern-based:\n\n"
            + "Customer support: draft replies, answer common questions.\n"
            + "Marketing: first drafts of emails, product descriptions, social posts.\n"
            + "Sales: summarise calls, prepare follow-ups.\n"
            + "Finance and admin: extract data from invoices, summarise reports.\n"
            + "HR: draft job ads, summarise applications for a human to review.\n\n"
            + "A good rule: if a task takes a person an hour of reading or typing, AI may cut it to minutes.",
            "Which task is a good first use of AI?",
            "Drafting replies to common customer questions|Setting the company's long-term strategy alone|Signing contracts",
            "0",
            "Repetitive, text-based work with a human check afterwards is the sweet spot."
        },
        {
            "3. Asking well (prompting)",
            "The quality of the answer follows the quality of the request. A good prompt has four parts:\n\n"
            + "1. Role: 'You are a friendly support agent for a bakery.'\n"
            + "2. Task: 'Reply to this complaint about a late order.'\n"
            + "3. Context: the order details, your refund policy, your tone.\n"
            + "4. Format: 'Three short paragraphs, end with an apology and a discount code.'\n\n"
            + "If the first answer is off, don't give up. Reply with what to change, like you would to a new employee.",
            "What makes a prompt better?",
            "Giving role, task, context and format|Keeping it as short as possible|Using only one word",
            "0",
            "More relevant context and a clear format give the AI far less to guess."
        },
        {
            "4. Know the limits",
            "AI can sound confident and still be wrong. This is called a 'hallucination': invented facts, "
            + "numbers or sources.\n\n"
            + "It can also repeat biases from its training data, and it only knows what it was trained on or what you give it.\n\n"
            + "Good habits: check facts and numbers before sending anything, keep a human responsible for the final "
            + "decision, and avoid pasting private customer details into tools your company has not approved.",
            "What is a 'hallucination' in AI?",
            "A confident answer that is actually made up or wrong|A screen glitch|A slow response",
            "0",
            "AI can invent plausible-sounding facts, so important outputs always need a human check."
        },
        {
            "5. Start small: run a pilot",
            "Don't transform the whole company at once.\n\n"
            + "1. Pick ONE task that is frequent, boring and low-risk.\n"
            + "2. Measure it today: time taken, errors, cost.\n"
            + "3. Try AI on it for 2 to 4 weeks with a few people.\n"
            + "4. Measure again and compare.\n"
            + "5. Keep it, improve it, or drop it. Then pick the next task.\n\n"
            + "Small wins build trust and show real numbers.",
            "What should come first when starting with AI?",
            "One small, low-risk task you can measure|Replace the whole team|Buy the most expensive tool",
            "0",
            "A small measured pilot shows real value with little risk and teaches your team what works."
        },
        {
            "6. Measuring the return (ROI)",
            "ROI means: what did we gain compared to what it cost?\n\n"
            + "Gain: hours saved x hourly cost, plus any extra sales or fewer mistakes.\n"
            + "Cost: tool subscriptions, setup time, training, and the time spent checking AI output.\n\n"
            + "Example: AI saves 10 hours a week at 20 per hour = 200 per week. If the tool and checking cost "
            + "50 per week, you gain 150 per week.\n\n"
            + "Remember the hidden cost: checking the work. Include it, or the numbers will look too good.",
            "Which cost is easy to forget when calculating AI's value?",
            "Time spent checking and fixing AI output|The office plants|Nothing, there are no hidden costs",
            "0",
            "Reviewing AI output takes time, so include it to get an honest return figure."
        }
    };

    private static final int BG = Color.rgb(18, 24, 38);
    private static final int CARD = Color.rgb(32, 42, 64);
    private static final int ACCENT = Color.rgb(255, 180, 70);
    private static final int GOOD = Color.rgb(90, 200, 120);
    private static final int BAD = Color.rgb(230, 100, 100);

    private int current = -1; // -1 = home screen
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (current < 0) showHome(); else showLesson(current);
    }

    @Override
    public void onBackPressed() {
        if (current >= 0) {
            current = -1;
            showHome();
        } else {
            super.onBackPressed();
        }
    }

    private int dp(int v) {
        return (int) (v * density);
    }

    private LinearLayout newPage() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(BG);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(16), dp(32), dp(16), dp(24));
        scroll.addView(page);
        setContentView(scroll);
        return page;
    }

    private TextView text(String s, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT_BOLD);
        t.setPadding(0, dp(6), 0, dp(6));
        return t;
    }

    private Button button(String label, int color) {
        Button b = new Button(this);
        b.setText(label);
        b.setAllCaps(false);
        b.setTextColor(Color.WHITE);
        b.setBackgroundColor(color);
        b.setTextSize(16);
        b.setGravity(android.view.Gravity.START | android.view.Gravity.CENTER_VERTICAL);
        b.setPadding(dp(16), dp(14), dp(16), dp(14));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, dp(6), 0, dp(6));
        b.setLayoutParams(lp);
        return b;
    }

    private void showHome() {
        LinearLayout page = newPage();
        page.addView(text("AI for Business", 28, Color.WHITE, true));
        page.addView(text("Six short lessons on using AI at work. Each ends with a quiz question.", 16, Color.LTGRAY, false));
        for (int i = 0; i < LESSONS.length; i++) {
            final int index = i;
            Button b = button(LESSONS[i][0], CARD);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = index;
                    showLesson(index);
                }
            });
            page.addView(b);
        }
    }

    private void showLesson(final int index) {
        String[] lesson = LESSONS[index];
        LinearLayout page = newPage();
        page.addView(text(lesson[0], 24, Color.WHITE, true));
        page.addView(text(lesson[1], 17, Color.rgb(220, 225, 235), false));
        page.addView(text("Quiz: " + lesson[2], 19, ACCENT, true));

        final TextView feedback = text("", 16, Color.WHITE, false);
        String[] options = lesson[3].split("\\|");
        final int correct = Integer.parseInt(lesson[4]);
        final String why = lesson[5];
        for (int i = 0; i < options.length; i++) {
            final int choice = i;
            final Button b = button(options[i], CARD);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choice == correct) {
                        b.setBackgroundColor(GOOD);
                        feedback.setTextColor(GOOD);
                        feedback.setText("Correct! " + why);
                    } else {
                        b.setBackgroundColor(BAD);
                        feedback.setTextColor(BAD);
                        feedback.setText("Not quite. Try another answer.");
                    }
                }
            });
            page.addView(b);
        }
        page.addView(feedback);

        if (index + 1 < LESSONS.length) {
            Button next = button("Next lesson", ACCENT);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current = index + 1;
                    showLesson(current);
                }
            });
            page.addView(next);
        }
        Button home = button("Back to lessons", CARD);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = -1;
                showHome();
            }
        });
        page.addView(home);
    }
}

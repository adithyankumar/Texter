# Texter
Texter is used to apply multiple styles and properties for a string set to textview. 

# Snippet:
Texter.into(tv,"Texter is my first library. " +
                "This library is used to apply multiple styles and properties for each character or words in a sentence. " +
                "You can also use image wherever you want smile. " +
                "Click Here! to pop Toast message.")
                .background(Color.RED,0,6)
                .foreground(R.color.colorPrimary,0,6)
                .underLine(58,66)
                .textStyle(Typeface.BOLD,0,6)
                .strikeOut("is","my")
                .background("#ffff00",1.5f,"my","Toast")
                .subScript("styles")
                .superScript("properties")
                .textStyle(Typeface.ITALIC,2f,"library")
               .imageSpan(R.drawable.smiling,"smile")
                .hyperLink(new OnHyperLinkClickListener() {
                    @Override
                    public void onClick(View var1) {
                        Toast.makeText(MainActivity.this, "Thanks for clicking.", Toast.LENGTH_SHORT).show();
                    }
                },true,"Click Here!")
                .build();

package com.kazimasum.recmvvm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kazimasum.recmvvm.R;
import com.kazimasum.recmvvm.models.CentermeetingDueData2.ObjEMICollectionDueDataShowItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AdaptorRecyclerviewCenterMetting extends RecyclerView.Adapter<AdaptorRecyclerviewCenterMetting.MyHolder> {

    private Context context;
    // private ArrayList<HashMap<String, String>> list;
    private List<ObjEMICollectionDueDataShowItem> list;
    private ArrayList<String> collected_emi = new ArrayList<>();

    public static ArrayList<HashMap<String, String>> LoanCalculate = new ArrayList<>();
    private View.OnClickListener attendanceListener;

    private AdapterView.OnItemSelectedListener select;
    //  private SpinnerAdapterDropdown adapter;
    //  public static LoanCollectionAdapter.CenterClick centerClick;

    //   public LoanCollectionAdapter.buttonCalculateLoan buttonCalculateLoan;
    //   public LoanCollectionAdapter.OnEditTextChanged onEditTextChanged;
    private boolean clicked = true;
    int count = 0;

    private int lastCheckedPosition = -1;

    int ans = 0;
    int iSubtract = 0;
    private OnItemCheckListener onItemCheck;
    private ItemClickListenerDiscount onItemClickCalculate;
    private ItemClickListenerPaid onItemClickCalPaid;
    String paypenality;


    public AdaptorRecyclerviewCenterMetting(Context context, List<ObjEMICollectionDueDataShowItem> objEMICollectionDueDataShow, OnItemCheckListener itemCheckListener, ItemClickListenerDiscount itemClickListenerDiscount, ItemClickListenerPaid itemClickListenerPaid/*, String peypenality*/) {
        this.context = context;
        this.list = objEMICollectionDueDataShow;
        this.onItemCheck = itemCheckListener;
        this.onItemClickCalculate = itemClickListenerDiscount;
        this.onItemClickCalPaid = itemClickListenerPaid;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Configuration config = context.getResources().getConfiguration();
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.listitem_loan_collection2, parent, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {

        try {

/*************************************Both Text Watcher********************************************************/
            holder.et_penalty_disc.removeTextChangedListener(holder.textWatcherDiscount);
            holder.tv_penalitydue.setText(list.get(position).getPenaltyPayableShow());
            holder.et_penalty_disc.addTextChangedListener(holder.textWatcherDiscount);


            holder.et_penalty_paid.removeTextChangedListener(holder.textWatcherPaid);
            holder.tv_penalitydue.setText(list.get(position).getPenaltyPayableShow());
            holder.et_penalty_paid.addTextChangedListener(holder.textWatcherPaid);
/****************************************************************************************************************/
            //  =======================================================================================================================
            holder.tv_memberId.setText(list.get(position).getLoanAcNo());
            holder.tv_memberName.setText(list.get(position).getAppName());
            holder.tv_EMI.setText(list.get(position).getEMI());
            holder.tv_Total_Due.setText("₹" + list.get(position).getTotalDue());

            holder.tv_noOfInstallment.setText(String.valueOf(list.get(position).getInstallmentNo()));
            holder.tv_collection.setText(String.valueOf("₹" + list.get(position).getPenaltyDue()));

/****************************************************************************************************************/
            if (list.get(position).getPaidPenalty() == null) {
                list.get(position).setPaidPenalty(list.get(position).getPenaltyDue());
            }
/***********************************Total due Calculate******************************************************************/
            if (list.get(position).getTotalDueDummy() == null) {
                list.get(position).setTotalDueDummy(list.get(position).getTotalDue());
                holder.tv_Total_Due.setText("₹" + list.get(position).getTotalDue());
            } else {
                list.get(position).setTotalDueDummy(list.get(position).getTotalDueDummy());
                holder.tv_Total_Due.setText("₹" + list.get(position).getTotalDueDummy());
            }


/***********************************When user not give penalty Discount******************************************************/


            if (list.get(position).getPenaltyDiscount() == null) {//PenaltyPaid is the editvalue of paid discount
                list.get(position).setPenaltyDiscount("0");
                holder.tv_penalitydue.setText((list.get(position).getPenaltyPayableShow()).replace(".0", ""));
                //  holder.payble_penalty2.setText(list.get(position).getPenaltyPayableShow());
                //holder.payble_penalty2.setText(list.get(position).getPenaltyPaidShow());
            } else {
                holder.tv_penalitydue.setText((list.get(position).getPenaltyPayableShow()).replace(".0", ""));
                // holder.payble_penalty2.setText(list.get(position).getPenaltyPayableShow());
                // holder.payble_penalty2.setText(list.get(position).getPenaltyPaidShow());
            }
/*************************************When user not give penalty paid******************************************************************/

/***************************************************************************************************/

            if (list.get(position).getPenaltyPaid() == null) {
                list.get(position).setPenaltyPaid("0");
            }
/*
            if (holder.et_penalty_paid.getText().toString().equalsIgnoreCase("0")) {

               // holder.et_penalty_paid.setText(list.get(position).getPenaltyPayableShow());
                Toast.makeText(context, "Enter Penalty paid", Toast.LENGTH_SHORT).show();
            }*/
            if (list.get(position).getPenaltyPaidShow() == null) {
                list.get(position).setPenaltyPaidShow(list.get(position).getPenaltyDue());
                holder.payble_penalty2.setText(list.get(position).getPenaltyPaidShow());

            } else {
                list.get(position).setPenaltyPaidShow(list.get(position).getPenaltyPaidShow());
                holder.payble_penalty2.setText(list.get(position).getPenaltyPaidShow());

            }


/***************************************************************************************************/

            if (list.get(position).getPaidShowSecond() == null) {
                Log.e("TAG", "AMAR2:" + String.valueOf(list.get(position).getPenaltyPayableShow()));
                list.get(position).setShowPaidOne(list.get(position).getPenaltyPayableShow());
                holder.tv_totalpaid.setText(String.valueOf(Integer.parseInt(list.get(position).getEMI()) + Integer.parseInt(list.get(position).getShowPaidOne())));
                list.get(position).setFinalTotalPaid(holder.tv_totalpaid.getText().toString());

            } else {
                Log.e("TAG", "Anshu" + String.valueOf(list.get(position).getPaidShowSecond()));
                list.get(position).setShowPaidOne(list.get(position).getPaidShowSecond());
                holder.tv_totalpaid.setText(String.valueOf(Integer.parseInt(list.get(position).getEMI()) + Integer.parseInt(list.get(position).getShowPaidOne())));
                list.get(position).setFinalTotalPaid(holder.tv_totalpaid.getText().toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.cb_select.setChecked(position <= lastCheckedPosition);

        holder.cb_select.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Check if the selected checkbox is within the allowed serial range
                if (position <= lastCheckedPosition + 1) {
                    lastCheckedPosition = position;
                    notifyDataSetChanged();

                    // Perform any other logic for the selected checkbox
                } else {
                    // Deselect the checkbox and show a message to the user
                    holder.cb_select.setChecked(false);
                    Toast.makeText(context, "Please select checkboxes in serial order.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Perform logic when a checkbox is deselected
            }
        });


      /*  if (holder instanceof MyHolder) {

            ((MyHolder) holder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyHolder) holder).cb_select.setChecked(
                            !((MyHolder) holder).cb_select.isChecked());
                    if (((MyHolder) holder).cb_select.isChecked()) {
                        onItemCheck.onItemCheck(currentItem, position);
                    } else {
                        onItemCheck.onItemUncheck(currentItem, position);
                    }
                }
            });
        }*/

       /* ObjEMICollectionDueDataShowItem collectionDueDataShowItem = list.get(position);
        holder.cb_select.setText("Installment " + list.get(position).getInstallmentNo());
        holder.cb_select.setChecked(collectionDueDataShowItem.isChecked());*/

       /* holder.cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ObjEMICollectionDueDataShowItem currentItem = list.get(position);

                if (((MyHolder) holder).cb_select.isChecked()) {
                    onItemCheck.onItemCheck(currentItem, position);
                } else {
                    onItemCheck.onItemUncheck(currentItem, position);
                }
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView payble_penalty2, tv_noOfInstallment, tv_Total_Due, tv_totalpaid, tv_memberId, tv_memberName, tv_EMI, tv_totalDue, tv_amount, tv_collection, tv_payablepenalty, tv_payablepenaltycalculte, tv_totalDuecalculate, tv_totalpaidcalculate, tv_penalitydue;
        private EditText et_penalty_disc, et_penalty_paid;
        private ImageView iv_attendance, finger;
        private RelativeLayout rl_attendance;
        private Spinner spinner;
        private Button bt_calculate, bt_calculatepaid;
        private CheckBox cb_select;
        View itemView;
        TextWatcher textWatcherDiscount;
        TextWatcher textWatcherPaid;

        private Handler handler = new Handler();
        private Runnable runnable;
        private static final long DELAY_MS = 1200;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_memberId = itemView.findViewById(R.id.tv_memberId);
            finger = itemView.findViewById(R.id.finger);
            tv_memberName = itemView.findViewById(R.id.tv_memberName);
            tv_Total_Due = itemView.findViewById(R.id.tv_Total_Due);
            tv_EMI = itemView.findViewById(R.id.tv_EMI);
            tv_totalDue = itemView.findViewById(R.id.tv_totalDue);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_collection = itemView.findViewById(R.id.tv_collection);
            //  iv_attendance = itemView.findViewById(R.id.iv_attendance);
            rl_attendance = itemView.findViewById(R.id.rl_attendance);
            spinner = itemView.findViewById(R.id.spinner);
            payble_penalty2 = itemView.findViewById(R.id.payble_penalty2);
            //  tv_payablepenalty = itemView.findViewById(R.id.tv_payablepenalty);
            tv_noOfInstallment = itemView.findViewById(R.id.tv_noOfInstallment);

            tv_totalpaid = itemView.findViewById(R.id.tv_totalpaid);
            tv_totalDue = itemView.findViewById(R.id.tv_totalDue);
            cb_select = itemView.findViewById(R.id.cb_select);
            // bt_calculatepaid = itemView.findViewById(R.id.bt_calculatepaid);
            et_penalty_paid = itemView.findViewById(R.id.et_penalty_paid);
            et_penalty_disc = itemView.findViewById(R.id.et_penalty_disc);

            tv_penalitydue = itemView.findViewById(R.id.tv_penalitydue);
           // cb_select.setClickable(false);


           /* cb_select.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ObjEMICollectionDueDataShowItem installment = list.get(position);
                    installment.setChecked(isChecked);

                    // Update UI or perform any desired action based on the checkbox selection
                    if (isChecked) {
                        // Checkbox is checked
                        // Enable/disable other checkboxes based on the selected installment
                        // For example, if installment 1 is selected, enable installment 2 checkbox and disable installment 1 checkbox
                    } else {
                        // Checkbox is unchecked
                        // Perform any desired action when the checkbox is unchecked
                    }
                }
            });*/


            textWatcherDiscount = new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    handler.removeCallbacks(runnable);

                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            // Perform your desired action after the delay
                            if (s != null) {
                                onItemClickCalculate.onClick(getAdapterPosition(), et_penalty_disc.getText().toString(),
                                        String.valueOf(list.get(getAdapterPosition()).getPenaltyDue()), String.valueOf(list.get(getAdapterPosition()).getTotalDue()));


                            }
                        }
                    };

                    // Schedule the execution of the runnable after the delay
                    handler.postDelayed(runnable, DELAY_MS);
                }


            };


            textWatcherPaid = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    handler.removeCallbacks(runnable);

                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            // Perform your desired action after the delay
                            if (s != null) {
                                payble_penalty2.setVisibility(View.GONE);
                                onItemClickCalPaid.onClick(getAdapterPosition(), et_penalty_paid.getText().toString(),
                                        String.valueOf(list.get(getAdapterPosition()).getPenaltyPaidShow()));
                            }
                        }
                    };

                    // Schedule the execution of the runnable after the delay
                    handler.postDelayed(runnable, DELAY_MS);
                }


            };


        }


        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }


    }

    public interface ItemClickListenerDiscount {
        void onClick(int position, String editvalue, String TotalDue, String TotalDueShow);

    }

    public interface ItemClickListenerPaid {
        void onClick(int position, String editvalue, String PenalityDue);

    }

    public interface ItemClickListenercheckbox {
        void onClickchebox(int position);

    }

    public interface OnItemCheckListener {
        void onItemCheck(ObjEMICollectionDueDataShowItem item, int position);

        void onItemUncheck(ObjEMICollectionDueDataShowItem item, int position);
    }


}

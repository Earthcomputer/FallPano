package net.earthcomputer.fallpano.mixin;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method = "get", at = @At(value = "INVOKE", target = "Ljava/util/Calendar;setTime(Ljava/util/Date;)V", shift = At.Shift.AFTER, remap = false), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void modifySplashText(CallbackInfoReturnable<String> ci, Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        if (calendar.get(Calendar.MONTH) >= Calendar.NOVEMBER) {
            year++;
        }

        LocalDate now = LocalDate.now();
        LocalDate halloween = LocalDate.of(year, Month.OCTOBER, 31);
        int days = (int) ChronoUnit.DAYS.between(now, halloween);

        if (days == 0) {
            ci.setReturnValue("Happy Halloween!");
        } else {
            ci.setReturnValue("Only " + days + " days until Halloween!");
        }
    }
}

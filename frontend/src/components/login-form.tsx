"use client";

import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { useEffect, useState } from "react";
import { Loader2 } from "lucide-react";
import { motion } from "motion/react";
import FloatingLabelInput from "./ui/floating-input";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { useAuth } from "./auth-provider";
import { toast } from "sonner";

export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"div">) {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const { login } = useAuth();
  const [initialRender, setInitialRender] = useState(true);

  useEffect(() => {
    setInitialRender(false);
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      await login(credentials);
      if (!document.startViewTransition) {
        router.push("/");
        toast.success("You are now logged in, welcome!");
        return;
      }

      document.startViewTransition(() => {
        router.push("/");
        toast.success("You are now logged in, welcome!");
      });
    } catch (err) {
      setError("Invalid credentials" + err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card className="overflow-hidden max-md:bg-neutral-50/95 py-0 max-md:dark:bg-neutral-950/90">
        <CardContent className="grid p-0 md:grid-cols-2">
          <div className="absolute flex overflow-hidden md:hidden w-screen h-screen bg-accent left-0 top-0 -z-1">
            <div className="flex">
              <img
                className="h-screen bg-cover w-screen object-cover brightness-100 dark:brightness-70"
                src="https://4kwallpapers.com/images/wallpapers/black-and-white-2560x1080-21293.jpg"
                alt=""
              />
            </div>
          </div>
          <form className="p-6 md:p-8" onSubmit={handleSubmit}>
            <div className="flex flex-col gap-4">
              <div className="flex flex-col items-center text-center">
                <h1 className="text-2xl font-bold">Welcome back</h1>
                <p className="text-balance text-muted-foreground">
                  Login to your ArticleWorm account
                </p>
              </div>
              <div className="">
                <FloatingLabelInput
                  id="text"
                  type="text"
                  label="Username"
                  value={credentials.username}
                  onChange={(e) =>
                    setCredentials({ ...credentials, username: e.target.value })
                  }
                  required
                />
              </div>
              <div className="pb-4">
                <FloatingLabelInput
                  label="Password"
                  type="password"
                  required
                  value={credentials.password}
                  onChange={(e) =>
                    setCredentials({ ...credentials, password: e.target.value })
                  }
                />
              </div>
              {error && <p className="text-red-500">{error}</p>}
              <Button
                type="submit"
                className={`w-full hover:cursor-pointer ${
                  initialRender ? "" : "transition-colors duration-200"
                } `}
                disabled={loading}
              >
                {loading ? (
                  <motion.div
                    key="loading"
                    initial={{ y: 20, opacity: 0 }}
                    animate={{ y: 0, opacity: 1 }}
                    exit={{ y: -20, opacity: 0 }}
                    transition={{ duration: 0.15, ease: "easeInOut" }}
                    className="w-full items-center justify-center flex flex-row"
                  >
                    <Loader2 className="animate-spin mr-2" />
                    <p>Please Wait</p>
                  </motion.div>
                ) : (
                  <motion.div
                    key="still"
                    initial={
                      initialRender
                        ? { y: 0, opacity: 1 }
                        : { y: -20, opacity: 0 }
                    }
                    animate={{ y: 0, opacity: 1 }}
                    exit={{ y: 20, opacity: 0 }}
                    transition={{ duration: 0.15, ease: "easeInOut" }}
                    className="text-center"
                  >
                    <p className="">Login</p>
                  </motion.div>
                )}
              </Button>
              <div className="text-center text-sm">
                Don&apos;t have an account?{" "}
                <Link href="/signup" className="underline underline-offset-4">
                  Sign up
                </Link>
              </div>
            </div>
          </form>
          <div className="h-full p-2 pl-0 hidden md:block relative">
            <div className="h-full w-full rounded-sm overflow-hidden flex relative">
              <img
                className="h-full bg-cover w-full object-cover brightness-125 dark:brightness-100"
                src="https://4kwallpapers.com/images/wallpapers/black-and-white-2560x1080-21293.jpg"
                alt=""
              />
              <div className="absolute w-full h-full text-center flex flex-col items-center justify-between">
                <div className="mt-2 px-3 flex flex-row w-full justify-between">
                  <div className="font-serif tracking-tight text-2xl dark:mix-blend-exclusion">
                    ArticleWorm
                  </div>
                  <Link href="/">
                    <Button className="rounded-full hover:bg-neutral-400/40 text-[#171717] bg-neutral-200/35 h-8 items-center transition-colors">
                      Back to Home
                    </Button>
                  </Link>
                </div>
                <div className="mb-4 text-[#e8e8e8] dark:mix-blend-exclusion leading-6.5 text-xl font-serif">
                  A place to read, write <br /> and deepen your understanding
                </div>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
